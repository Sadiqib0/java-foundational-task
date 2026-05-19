package com.dreamorder.orderService.service;

import com.dreamorder.orderService.client.InventoryClient;
import com.dreamorder.orderService.client.WalletClient;
import com.dreamorder.orderService.data.model.Order;
import com.dreamorder.orderService.data.model.OrderItem;
import com.dreamorder.orderService.data.model.ShippingAddress;
import com.dreamorder.orderService.data.repository.OrderRepository;
import com.dreamorder.orderService.dto.request.PlaceOrderItemRequest;
import com.dreamorder.orderService.dto.request.PlaceOrderRequest;
import com.dreamorder.orderService.dto.request.PlaceOrderShippingAddressRequest;
import com.dreamorder.orderService.dto.response.OrderItemResponse;
import com.dreamorder.orderService.dto.response.OrderStatusResponse;
import com.dreamorder.orderService.dto.response.PlaceOrderResponse;
import com.dreamorder.orderService.enums.OrderStatus;
import com.dreamorder.orderService.event.OrderEventPublisher;
import com.dreamorder.orderService.exception.CancellationWindowExpiredException;
import com.dreamorder.orderService.exception.ForbiddenOrderAccessException;
import com.dreamorder.orderService.exception.IdempotencyKeyRequiredException;
import com.dreamorder.orderService.exception.InsufficientStockException;
import com.dreamorder.orderService.exception.InvalidOrderStatusTransitionException;
import com.dreamorder.orderService.exception.OrderNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;
    private final WalletClient walletClient;
    private final OrderEventPublisher orderEventPublisher;

    @Value("${order.cancel-window-mins:60}")
    private long cancellationWindowMinutes = 60;

    @Override
    @Transactional
    public PlaceOrderResponse placeOrder(String buyerId, PlaceOrderRequest request) {
        return placeOrder(buyerId, null, request);
    }

    @Override
    @Transactional
    public PlaceOrderResponse placeOrder(String buyerId, String idempotencyKey, PlaceOrderRequest request) {
        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IdempotencyKeyRequiredException("X-Idempotency-Key header is required");
        }

        return orderRepository.findByIdempotencyKey(idempotencyKey)
                .map(this::mapToResponse)
                .orElseGet(() -> createOrder(buyerId, idempotencyKey, request));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean idempotencyKeyExists(String idempotencyKey) {
        return idempotencyKey != null
                && !idempotencyKey.isBlank()
                && orderRepository.findByIdempotencyKey(idempotencyKey).isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlaceOrderResponse> listOrders(String buyerId) {
        return orderRepository.findByBuyerIdOrderByCreatedAtDesc(buyerId).stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PlaceOrderResponse getOrder(String buyerId, String orderId) {
        return mapToResponse(findBuyerOrder(buyerId, orderId));
    }

    @Override
    @Transactional(readOnly = true)
    public OrderStatusResponse getOrderStatus(String buyerId, String orderId) {
        Order order = findBuyerOrder(buyerId, orderId);
        return OrderStatusResponse.builder()
                .orderId(order.getId())
                .status(order.getStatus())
                .build();
    }

    @Override
    @Transactional
    public PlaceOrderResponse cancelOrder(String buyerId, String orderId) {
        Order order = findBuyerOrder(buyerId, orderId);
        ensureBuyerCancellationAllowed(order);
        cancel(order, "Buyer cancelled order", false);
        Order saved = orderRepository.save(order);
        orderEventPublisher.publishOrderCancelled(saved);
        return mapToResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PlaceOrderResponse> listSellerOrders(Collection<String> productIds) {
        if (productIds == null || productIds.isEmpty()) {
            return List.of();
        }
        return orderRepository.findDistinctByItemsProductIdInOrderByCreatedAtDesc(productIds).stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional
    public PlaceOrderResponse updateStatus(String orderId, OrderStatus status, String reason, boolean privileged) {
        if (!privileged) {
            throw new ForbiddenOrderAccessException("Only internal or admin callers can update order status");
        }

        Order order = findOrder(orderId);
        if (order.getStatus() == status) {
            return mapToResponse(order);
        }

        switch (status) {
            case CONFIRMED -> confirm(order);
            case FAILED -> fail(order);
            case CANCELLED -> cancel(order, reason == null || reason.isBlank() ? "Admin cancelled order" : reason, true);
            default -> throw new InvalidOrderStatusTransitionException("Unsupported status transition to " + status);
        }

        Order saved = orderRepository.save(order);
        publishForStatus(saved);
        return mapToResponse(saved);
    }

    @Override
    @Transactional
    public PlaceOrderResponse handlePaymentCompleted(String orderId) {
        Order order = findOrder(orderId);
        confirm(order);
        Order saved = orderRepository.save(order);
        orderEventPublisher.publishOrderConfirmed(saved);
        return mapToResponse(saved);
    }

    @Override
    @Transactional
    public PlaceOrderResponse handlePaymentFailed(String orderId) {
        Order order = findOrder(orderId);
        fail(order);
        Order saved = orderRepository.save(order);
        return mapToResponse(saved);
    }

    private PlaceOrderResponse createOrder(String buyerId, String idempotencyKey, PlaceOrderRequest request) {
        Order order = buildOrder(buyerId, idempotencyKey, request);
        order = orderRepository.save(order);

        boolean reserved = inventoryClient.reserveItems(request.getItems());
        if (!reserved) {
            order.setStatus(OrderStatus.FAILED);
            order.setUpdatedAt(LocalDateTime.now());
            orderRepository.save(order);
            throw new InsufficientStockException("Insufficient stock for one or more items");
        }

        order.setStatus(OrderStatus.RESERVED);
        order.setUpdatedAt(LocalDateTime.now());
        Order saved = orderRepository.save(order);
        orderEventPublisher.publishOrderPlaced(saved);

        return mapToResponse(saved);
    }

    private Order buildOrder(String buyerId, String idempotencyKey, PlaceOrderRequest request) {
        Order order = new Order();
        order.setBuyerId(buyerId);
        order.setIdempotencyKey(idempotencyKey);
        order.setPaymentMethod(request.getPaymentMethod());
        order.setShippingAddress(mapShippingAddress(request.getShippingAddress()));

        long total = 0;
        for (PlaceOrderItemRequest itemReq : request.getItems()) {
            OrderItem item = new OrderItem();
            item.setProductId(itemReq.getProductId());
            item.setQuantity(itemReq.getQuantity());
            item.setUnitPrice(itemReq.getUnitPrice());
            item.setOrder(order);
            order.getItems().add(item);
            total += itemReq.getUnitPrice() * itemReq.getQuantity();
        }
        order.setTotalAmount(total);
        return order;
    }

    private ShippingAddress mapShippingAddress(PlaceOrderShippingAddressRequest req) {
        ShippingAddress address = new ShippingAddress();
        address.setStreet(req.getStreet());
        address.setCity(req.getCity());
        address.setState(req.getState());
        address.setCountry(req.getCountry());
        return address;
    }

    private Order findBuyerOrder(String buyerId, String orderId) {
        return orderRepository.findByIdAndBuyerId(orderId, buyerId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }

    private Order findOrder(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }

    private void ensureBuyerCancellationAllowed(Order order) {
        if (order.getStatus() == OrderStatus.CONFIRMED) {
            throw new InvalidOrderStatusTransitionException("Confirmed orders can only be cancelled by an admin");
        }
        if (order.getStatus() == OrderStatus.CANCELLED || order.getStatus() == OrderStatus.FAILED) {
            throw new InvalidOrderStatusTransitionException("Order cannot be cancelled from status " + order.getStatus());
        }
        LocalDateTime expiresAt = order.getCreatedAt().plusMinutes(cancellationWindowMinutes);
        if (LocalDateTime.now().isAfter(expiresAt)) {
            throw new CancellationWindowExpiredException("Cancellation window has expired");
        }
    }

    private void confirm(Order order) {
        if (order.getStatus() != OrderStatus.RESERVED) {
            throw new InvalidOrderStatusTransitionException("Only RESERVED orders can be confirmed");
        }
        inventoryClient.confirmReservation(order.getId());
        order.setStatus(OrderStatus.CONFIRMED);
        order.setUpdatedAt(LocalDateTime.now());
    }

    private void fail(Order order) {
        if (order.getStatus() != OrderStatus.RESERVED && order.getStatus() != OrderStatus.PENDING) {
            throw new InvalidOrderStatusTransitionException("Order cannot fail from status " + order.getStatus());
        }
        if (order.getStatus() == OrderStatus.RESERVED) {
            inventoryClient.releaseReservation(order.getId());
        }
        order.setStatus(OrderStatus.FAILED);
        order.setUpdatedAt(LocalDateTime.now());
    }

    private void cancel(Order order, String reason, boolean adminCancellation) {
        if (order.getStatus() == OrderStatus.CANCELLED) {
            return;
        }
        if (order.getStatus() == OrderStatus.FAILED) {
            throw new InvalidOrderStatusTransitionException("Failed orders cannot be cancelled");
        }
        if (order.getStatus() == OrderStatus.CONFIRMED && !adminCancellation) {
            throw new InvalidOrderStatusTransitionException("Confirmed orders can only be cancelled by an admin");
        }
        if (order.getStatus() == OrderStatus.RESERVED) {
            inventoryClient.releaseReservation(order.getId());
        }
        if (order.getStatus() == OrderStatus.CONFIRMED) {
            walletClient.refund(order);
        }
        order.setStatus(OrderStatus.CANCELLED);
        order.setCancelledAt(LocalDateTime.now());
        order.setCancelReason(reason);
        order.setUpdatedAt(LocalDateTime.now());
    }

    private void publishForStatus(Order order) {
        if (order.getStatus() == OrderStatus.CONFIRMED) {
            orderEventPublisher.publishOrderConfirmed(order);
        }
        if (order.getStatus() == OrderStatus.CANCELLED) {
            orderEventPublisher.publishOrderCancelled(order);
        }
    }

    private PlaceOrderResponse mapToResponse(Order order) {
        List<OrderItemResponse> items = order.getItems().stream()
                .map(item -> OrderItemResponse.builder()
                        .id(item.getId())
                        .productId(item.getProductId())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .build())
                .toList();

        return PlaceOrderResponse.builder()
                .id(order.getId())
                .buyerId(order.getBuyerId())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .currency(order.getCurrency())
                .paymentMethod(order.getPaymentMethod())
                .items(items)
                .shippingAddress(mapShippingAddressResponse(order.getShippingAddress()))
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .cancelledAt(order.getCancelledAt())
                .cancelReason(order.getCancelReason())
                .build();
    }

    private PlaceOrderResponse.ShippingAddressResponse mapShippingAddressResponse(ShippingAddress address) {
        if (address == null) {
            return null;
        }
        return PlaceOrderResponse.ShippingAddressResponse.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .build();
    }
}

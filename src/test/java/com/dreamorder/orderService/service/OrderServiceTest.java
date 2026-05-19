package com.dreamorder.orderService.service;

import com.dreamorder.orderService.client.InventoryClient;
import com.dreamorder.orderService.client.WalletClient;
import com.dreamorder.orderService.data.model.Order;
import com.dreamorder.orderService.data.repository.OrderRepository;
import com.dreamorder.orderService.dto.request.PlaceOrderItemRequest;
import com.dreamorder.orderService.dto.request.PlaceOrderRequest;
import com.dreamorder.orderService.dto.request.PlaceOrderShippingAddressRequest;
import com.dreamorder.orderService.dto.response.PlaceOrderResponse;
import com.dreamorder.orderService.enums.OrderStatus;
import com.dreamorder.orderService.event.OrderEventPublisher;
import com.dreamorder.orderService.exception.CancellationWindowExpiredException;
import com.dreamorder.orderService.exception.IdempotencyKeyRequiredException;
import com.dreamorder.orderService.exception.InsufficientStockException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private InventoryClient inventoryClient;

    @Mock
    private WalletClient walletClient;

    @Mock
    private OrderEventPublisher orderEventPublisher;

    @InjectMocks
    private OrderServiceImpl orderService;

    private PlaceOrderRequest buildRequest(String productId, int qty, long unitPrice) {
        PlaceOrderItemRequest item = new PlaceOrderItemRequest();
        item.setProductId(productId);
        item.setQuantity(qty);
        item.setUnitPrice(unitPrice);

        PlaceOrderShippingAddressRequest address = new PlaceOrderShippingAddressRequest();
        address.setStreet("10 Broad Street");
        address.setCity("Lagos");
        address.setState("Lagos");
        address.setCountry("NG");

        PlaceOrderRequest request = new PlaceOrderRequest();
        request.setItems(List.of(item));
        request.setShippingAddress(address);
        request.setPaymentMethod("WALLET");
        return request;
    }

    @Test
    @DisplayName("Place order - inventory available → response has RESERVED status")
    void placeOrder_inventoryAvailable_returnsReservedOrder() {
        PlaceOrderRequest request = buildRequest("prod-abc", 2, 45000L);
        when(inventoryClient.reserveItems(any())).thenReturn(true);
        when(orderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(orderRepository.findByIdempotencyKey("idem-1")).thenReturn(Optional.empty());

        PlaceOrderResponse response = orderService.placeOrder("buyer-123", "idem-1", request);

        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(OrderStatus.RESERVED);
        assertThat(response.getBuyerId()).isEqualTo("buyer-123");
        verify(orderEventPublisher).publishOrderPlaced(any(Order.class));
    }

    @Test
    @DisplayName("Place order - total amount = sum of (qty * unitPrice) across all items")
    void placeOrder_calculatesTotalAmountCorrectly() {
        PlaceOrderRequest request = buildRequest("prod-abc", 2, 45000L);
        when(inventoryClient.reserveItems(any())).thenReturn(true);
        when(orderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(orderRepository.findByIdempotencyKey("idem-2")).thenReturn(Optional.empty());

        PlaceOrderResponse response = orderService.placeOrder("buyer-123", "idem-2", request);

        assertThat(response.getTotalAmount()).isEqualTo(90000L);
    }

    @Test
    @DisplayName("Place order - unit price is snapshotted at time of order, not looked up later")
    void placeOrder_snapshotsUnitPriceFromRequest() {
        PlaceOrderRequest request = buildRequest("prod-abc", 1, 45000L);
        when(inventoryClient.reserveItems(any())).thenReturn(true);
        when(orderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(orderRepository.findByIdempotencyKey("idem-3")).thenReturn(Optional.empty());

        PlaceOrderResponse response = orderService.placeOrder("buyer-123", "idem-3", request);

        assertThat(response.getItems().get(0).getUnitPrice()).isEqualTo(45000L);
    }

    @Test
    @DisplayName("Place order - inventory unavailable → throws InsufficientStockException")
    void placeOrder_inventoryUnavailable_throwsException() {
        PlaceOrderRequest request = buildRequest("prod-abc", 5, 45000L);
        when(inventoryClient.reserveItems(any())).thenReturn(false);
        when(orderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(orderRepository.findByIdempotencyKey("idem-4")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.placeOrder("buyer-123", "idem-4", request))
                .isInstanceOf(InsufficientStockException.class);
    }

    @Test
    @DisplayName("Place order - inventory unavailable → order is saved with FAILED status before throwing")
    void placeOrder_inventoryUnavailable_savesOrderAsFailed() {
        PlaceOrderRequest request = buildRequest("prod-abc", 5, 45000L);
        when(inventoryClient.reserveItems(any())).thenReturn(false);
        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        when(orderRepository.save(captor.capture())).thenAnswer(inv -> inv.getArgument(0));
        when(orderRepository.findByIdempotencyKey("idem-5")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.placeOrder("buyer-123", "idem-5", request))
                .isInstanceOf(InsufficientStockException.class);

        assertThat(captor.getAllValues().getLast().getStatus()).isEqualTo(OrderStatus.FAILED);
    }

    @Test
    @DisplayName("Place order - payment method is persisted from request")
    void placeOrder_persistsPaymentMethod() {
        PlaceOrderRequest request = buildRequest("prod-abc", 1, 45000L);
        when(inventoryClient.reserveItems(any())).thenReturn(true);
        when(orderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));
        when(orderRepository.findByIdempotencyKey("idem-6")).thenReturn(Optional.empty());

        PlaceOrderResponse response = orderService.placeOrder("buyer-123", "idem-6", request);

        assertThat(response.getPaymentMethod()).isEqualTo("WALLET");
    }

    @Test
    @DisplayName("Place order - missing idempotency key → throws IdempotencyKeyRequiredException")
    void placeOrder_missingIdempotencyKey_throwsException() {
        PlaceOrderRequest request = buildRequest("prod-abc", 1, 45000L);

        assertThatThrownBy(() -> orderService.placeOrder("buyer-123", null, request))
                .isInstanceOf(IdempotencyKeyRequiredException.class);
    }

    @Test
    @DisplayName("Place order - duplicate idempotency key → returns original order")
    void placeOrder_duplicateIdempotencyKey_returnsOriginalOrder() {
        Order existing = new Order();
        existing.setId("order-1");
        existing.setBuyerId("buyer-123");
        existing.setStatus(OrderStatus.RESERVED);
        existing.setTotalAmount(90000L);
        existing.setPaymentMethod("WALLET");
        existing.setIdempotencyKey("idem-7");
        when(orderRepository.findByIdempotencyKey("idem-7")).thenReturn(Optional.of(existing));

        PlaceOrderResponse response = orderService.placeOrder("buyer-123", "idem-7", buildRequest("prod-abc", 2, 45000L));

        assertThat(response.getId()).isEqualTo("order-1");
        assertThat(response.getStatus()).isEqualTo(OrderStatus.RESERVED);
    }

    @Test
    @DisplayName("Payment completed - RESERVED order → CONFIRMED and inventory confirmed")
    void handlePaymentCompleted_reservedOrder_confirmsOrder() {
        Order order = reservedOrder();
        when(orderRepository.findById("order-1")).thenReturn(Optional.of(order));
        when(orderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        PlaceOrderResponse response = orderService.handlePaymentCompleted("order-1");

        assertThat(response.getStatus()).isEqualTo(OrderStatus.CONFIRMED);
        verify(inventoryClient).confirmReservation("order-1");
        verify(orderEventPublisher).publishOrderConfirmed(order);
    }

    @Test
    @DisplayName("Payment failed - RESERVED order → FAILED and reservation released")
    void handlePaymentFailed_reservedOrder_failsOrder() {
        Order order = reservedOrder();
        when(orderRepository.findById("order-1")).thenReturn(Optional.of(order));
        when(orderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        PlaceOrderResponse response = orderService.handlePaymentFailed("order-1");

        assertThat(response.getStatus()).isEqualTo(OrderStatus.FAILED);
        verify(inventoryClient).releaseReservation("order-1");
    }

    @Test
    @DisplayName("Cancel order - RESERVED within window → CANCELLED and reservation released")
    void cancelOrder_reservedWithinWindow_cancelsOrder() {
        Order order = reservedOrder();
        when(orderRepository.findByIdAndBuyerId("order-1", "buyer-123")).thenReturn(Optional.of(order));
        when(orderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        PlaceOrderResponse response = orderService.cancelOrder("buyer-123", "order-1");

        assertThat(response.getStatus()).isEqualTo(OrderStatus.CANCELLED);
        assertThat(response.getCancelReason()).isEqualTo("Buyer cancelled order");
        verify(inventoryClient).releaseReservation("order-1");
        verify(orderEventPublisher).publishOrderCancelled(order);
    }

    @Test
    @DisplayName("Cancel order - outside cancellation window → throws CancellationWindowExpiredException")
    void cancelOrder_outsideWindow_throwsException() {
        Order order = reservedOrder();
        order.setCreatedAt(LocalDateTime.now().minusMinutes(90));
        when(orderRepository.findByIdAndBuyerId("order-1", "buyer-123")).thenReturn(Optional.of(order));

        assertThatThrownBy(() -> orderService.cancelOrder("buyer-123", "order-1"))
                .isInstanceOf(CancellationWindowExpiredException.class);
    }

    @Test
    @DisplayName("Admin status update - CONFIRMED to CANCELLED → refunds wallet")
    void updateStatus_confirmedToCancelled_refundsWallet() {
        Order order = reservedOrder();
        order.setStatus(OrderStatus.CONFIRMED);
        when(orderRepository.findById("order-1")).thenReturn(Optional.of(order));
        when(orderRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        PlaceOrderResponse response = orderService.updateStatus("order-1", OrderStatus.CANCELLED, "fraud review", true);

        assertThat(response.getStatus()).isEqualTo(OrderStatus.CANCELLED);
        verify(walletClient).refund(order);
    }

    private Order reservedOrder() {
        Order order = new Order();
        order.setId("order-1");
        order.setBuyerId("buyer-123");
        order.setStatus(OrderStatus.RESERVED);
        order.setTotalAmount(90000L);
        order.setPaymentMethod("WALLET");
        order.setCreatedAt(LocalDateTime.now().minusMinutes(20));
        order.setUpdatedAt(LocalDateTime.now().minusMinutes(20));

        com.dreamorder.orderService.data.model.ShippingAddress address = new com.dreamorder.orderService.data.model.ShippingAddress();
        address.setStreet("10 Broad Street");
        address.setCity("Lagos");
        address.setState("Lagos");
        address.setCountry("NG");
        order.setShippingAddress(address);
        return order;
    }
}

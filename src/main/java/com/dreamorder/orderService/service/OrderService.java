package com.dreamorder.orderService.service;

import com.dreamorder.orderService.dto.request.PlaceOrderRequest;
import com.dreamorder.orderService.dto.response.OrderStatusResponse;
import com.dreamorder.orderService.dto.response.PlaceOrderResponse;
import com.dreamorder.orderService.enums.OrderStatus;

import java.util.Collection;
import java.util.List;

public interface OrderService {
    PlaceOrderResponse placeOrder(String buyerId, PlaceOrderRequest request);

    PlaceOrderResponse placeOrder(String buyerId, String idempotencyKey, PlaceOrderRequest request);

    boolean idempotencyKeyExists(String idempotencyKey);

    List<PlaceOrderResponse> listOrders(String buyerId);

    PlaceOrderResponse getOrder(String buyerId, String orderId);

    OrderStatusResponse getOrderStatus(String buyerId, String orderId);

    PlaceOrderResponse cancelOrder(String buyerId, String orderId);

    List<PlaceOrderResponse> listSellerOrders(Collection<String> productIds);

    PlaceOrderResponse updateStatus(String orderId, OrderStatus status, String reason, boolean privileged);

    PlaceOrderResponse handlePaymentCompleted(String orderId);

    PlaceOrderResponse handlePaymentFailed(String orderId);
}

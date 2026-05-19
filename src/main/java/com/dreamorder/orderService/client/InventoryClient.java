package com.dreamorder.orderService.client;

import com.dreamorder.orderService.dto.request.PlaceOrderItemRequest;

import java.util.List;

public interface InventoryClient {
    boolean reserveItems(List<PlaceOrderItemRequest> items);

    void releaseReservation(String orderId);

    void confirmReservation(String orderId);
}

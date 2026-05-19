package com.dreamorder.orderService.client;

import com.dreamorder.orderService.dto.request.PlaceOrderItemRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryClientImpl implements InventoryClient {

    @Override
    public boolean reserveItems(List<PlaceOrderItemRequest> items) {
        return true;
    }

    @Override
    public void releaseReservation(String orderId) {
        // HTTP integration seam: inventory-service release reservation for orderId.
    }

    @Override
    public void confirmReservation(String orderId) {
        // HTTP integration seam: inventory-service confirm/decrement reservation for orderId.
    }
}

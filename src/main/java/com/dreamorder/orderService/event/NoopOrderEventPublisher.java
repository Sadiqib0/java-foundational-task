package com.dreamorder.orderService.event;

import com.dreamorder.orderService.data.model.Order;
import org.springframework.stereotype.Component;

@Component
public class NoopOrderEventPublisher implements OrderEventPublisher {

    @Override
    public void publishOrderPlaced(Order order) {
        // Kafka integration seam: ORDER_PLACED.
    }

    @Override
    public void publishOrderConfirmed(Order order) {
        // Kafka integration seam: ORDER_CONFIRMED.
    }

    @Override
    public void publishOrderCancelled(Order order) {
        // Kafka integration seam: ORDER_CANCELLED.
    }
}

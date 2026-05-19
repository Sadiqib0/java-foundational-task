package com.dreamorder.orderService.event;

import com.dreamorder.orderService.data.model.Order;

public interface OrderEventPublisher {
    void publishOrderPlaced(Order order);

    void publishOrderConfirmed(Order order);

    void publishOrderCancelled(Order order);
}

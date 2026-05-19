package com.dreamorder.orderService.client;

import com.dreamorder.orderService.data.model.Order;

public interface WalletClient {
    void refund(Order order);
}

package com.dreamorder.orderService.client;

import com.dreamorder.orderService.data.model.Order;
import org.springframework.stereotype.Component;

@Component
public class WalletClientImpl implements WalletClient {

    @Override
    public void refund(Order order) {
        // HTTP integration seam: wallet-service refund flow for confirmed cancellations.
    }
}

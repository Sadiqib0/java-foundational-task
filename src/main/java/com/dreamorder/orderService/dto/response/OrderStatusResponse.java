package com.dreamorder.orderService.dto.response;

import com.dreamorder.orderService.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderStatusResponse {
    private String orderId;
    private OrderStatus status;
}

package com.dreamorder.orderService.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemResponse {
    private String id;
    private String productId;
    private Integer quantity;
    private Long unitPrice;
}

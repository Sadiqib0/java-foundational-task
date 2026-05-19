package com.dreamorder.orderService.dto.response;

import com.dreamorder.orderService.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class PlaceOrderResponse {
    private String id;
    private String buyerId;
    private OrderStatus status;
    private Long totalAmount;
    private String currency;
    private String paymentMethod;
    private List<OrderItemResponse> items;
    private ShippingAddressResponse shippingAddress;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime cancelledAt;
    private String cancelReason;

    @Getter
    @Builder
    public static class ShippingAddressResponse {
        private String street;
        private String city;
        private String state;
        private String country;
    }
}

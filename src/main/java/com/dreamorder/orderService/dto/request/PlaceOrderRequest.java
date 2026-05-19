package com.dreamorder.orderService.dto.request;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlaceOrderRequest {
    @NotEmpty(message = "items is required")
    private List<@Valid PlaceOrderItemRequest> items;

    @NotNull(message = "shippingAddress is required")
    @Valid
    private PlaceOrderShippingAddressRequest shippingAddress;

    @NotNull(message = "paymentMethod is required")
    @Pattern(regexp = "WALLET|CARD|BANK_TRANSFER", message = "paymentMethod must be WALLET, CARD, or BANK_TRANSFER")
    private String paymentMethod;
}

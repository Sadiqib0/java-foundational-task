package com.dreamorder.orderService.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceOrderItemRequest {
    @NotBlank(message = "productId is required")
    private String productId;

    @NotNull(message = "quantity is required")
    @Min(value = 1, message = "quantity must be greater than zero")
    private Integer quantity;

    @NotNull(message = "unitPrice is required")
    @Positive(message = "unitPrice must be greater than zero")
    private Long unitPrice;

    public Integer getQty() {
        return quantity;
    }

    public void setQty(Integer qty) {
        this.quantity = qty;
    }
}

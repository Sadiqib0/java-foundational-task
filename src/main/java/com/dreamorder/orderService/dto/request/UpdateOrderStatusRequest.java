package com.dreamorder.orderService.dto.request;

import com.dreamorder.orderService.enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderStatusRequest {
    @NotNull(message = "status is required")
    private OrderStatus status;

    private String reason;
}

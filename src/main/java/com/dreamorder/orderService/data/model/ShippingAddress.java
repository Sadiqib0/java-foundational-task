package com.dreamorder.orderService.data.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ShippingAddress {
    private String street;
    private String city;
    private String state;
    private String country;
}

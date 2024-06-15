package com.example.cart_service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@RequiredArgsConstructor
@SuperBuilder
@EqualsAndHashCode
public class ProductDto {
    private final String name;
    private final Double price;
}

package com.example.cart_service.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Getter
@RequiredArgsConstructor
@SuperBuilder
@EqualsAndHashCode
public class CartDto {
    private String uuid;
    private Map<ProductDto, Integer> products;
    private double totalPrice;
}

package com.example.cart_service.dto;

import com.example.cart_service.entity.CartItem;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;


@Getter
@RequiredArgsConstructor
@SuperBuilder
@EqualsAndHashCode
public class CartDto {
    private String uuid;
    private List<CartItem> products;
    private double totalPrice;
}

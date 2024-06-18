package com.example.cart_service.utils;

import com.example.cart_service.entity.Cart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceCalculator {
    public void calculatePrice(Cart cart) {
        double total = cart.getProducts().stream()
                .mapToDouble(cartItem -> cartItem.getProduct().getPrice() * cartItem.getQuantity())
                .sum();
        cart.setTotalPrice(total);
    }
}
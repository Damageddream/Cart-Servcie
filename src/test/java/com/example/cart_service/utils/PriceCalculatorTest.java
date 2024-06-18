package com.example.cart_service.utils;

import com.example.cart_service.dto.ProductDto;
import com.example.cart_service.entity.Cart;
import com.example.cart_service.entity.CartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

public class PriceCalculatorTest {
    private PriceCalculator priceCalculator;

    @BeforeEach
    void setUp() {
        priceCalculator = new PriceCalculator();
    }

    @Test
    void testGetPrice() {
        //given
        ProductDto product1 = ProductDto.builder()
                .price(1.5)
                .name("Phone")
                .build();
        ProductDto product2 = ProductDto.builder()
                .price(3.5)
                .name("Computer")
                .build();
        CartItem cartItem1 = CartItem.builder()
                .product(product1)
                .quantity(3)
                .build();
        CartItem cartItem2 = CartItem.builder()
                .product(product2)
                .quantity(2)
                .build();

        List<CartItem> products = List.of(cartItem1, cartItem2);
        Cart cart  = Cart.builder()
                .id("1")
                .uuid("123")
                .totalPrice(0.0)
                .products(products)
                .build();

        priceCalculator.calculatePrice(cart);

        assertEquals(11.5, cart.getTotalPrice());

    }
}

package com.example.cart_service.service;

import com.example.cart_service.dto.CartDto;
import com.example.cart_service.dto.ProductDto;
import org.springframework.transaction.annotation.Transactional;

public interface CartService {
    CartDto getCart(String uuid);
    CartDto createCart();
    CartDto addToCart(ProductDto product, String uuid);
    CartDto removeFromCart(ProductDto product, String uuid);
    void clearCart(String uuid);
    void deleteCart(String uuid);
}

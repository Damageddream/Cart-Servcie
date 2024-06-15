package com.example.cart_service.service;

import com.example.cart_service.dto.CartDto;
import com.example.cart_service.dto.ProductDto;
import com.example.cart_service.dto.mapper.CartMapper;
import com.example.cart_service.entity.Cart;
import com.example.cart_service.exception.CartNotFoundException;
import com.example.cart_service.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private CartRepository cartRepository;
    private CartMapper cartMapper;

    @Override
    public CartDto getCart(String uuid) {
        Cart cart = cartRepository.findByUuid(uuid)
                .orElseThrow(()-> new CartNotFoundException("Cart not found"));
        return cartMapper.toCartDto(cart);
    }

    @Override
    @Transactional
    public CartDto createCart() {
        Cart cart = Cart.builder()
                .uuid(UUID.randomUUID().toString())
                .products(new HashMap<>())
                .totalPrice(0.0)
                .build();
        cartRepository.save(cart);
        return cartMapper.toCartDto(cart);
    }

    @Override
    public CartDto addToCart(ProductDto product) {

        return null;
    }

    @Override
    public CartDto removeFromCart(ProductDto product) {
        return null;
    }

    @Override
    public void clearCart(String uuid) {

    }

    @Override
    public void deleteCart(String uuid) {

    }
}

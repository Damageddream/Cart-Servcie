package com.example.cart_service.service;

import com.example.cart_service.dto.CartDto;
import com.example.cart_service.dto.ProductDto;
import com.example.cart_service.dto.mapper.CartMapper;
import com.example.cart_service.entity.Cart;
import com.example.cart_service.entity.CartItem;
import com.example.cart_service.exception.CartNotFoundException;
import com.example.cart_service.repository.CartRepository;
import com.example.cart_service.utils.PriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final PriceCalculator priceCalculator;

    @Override
    public CartDto getCart(String uuid) {
        Cart cart = cartRepository.findByUuid(uuid)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));
        return cartMapper.toCartDto(cart);
    }

    @Override
    @Transactional
    public CartDto createCart() {
        Cart cart = Cart.builder()
                .uuid(UUID.randomUUID().toString())
                .products(new ArrayList<>())
                .totalPrice(0.0)
                .build();
        cartRepository.save(cart);
        return cartMapper.toCartDto(cart);
    }

    @Override
    @Transactional
    public CartDto addToCart(ProductDto product, String uuid) {
        Cart cart = cartRepository.findByUuid(uuid)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));
        List<CartItem> cartItems = cart.getProducts();
        cartItems.stream()
                .filter(cartItem -> cartItem.getProduct().equals(product))
                .findFirst()
                .ifPresentOrElse(existingItem -> existingItem.setQuantity(existingItem.getQuantity() + 1),
                        () -> cartItems.add(new CartItem(product, 1)));
        cart.setProducts(cartItems);
        priceCalculator.calculatePrice(cart);
        cartRepository.save(cart);
        return cartMapper.toCartDto(cart);
    }

    @Override
    @Transactional
    public CartDto removeFromCart(ProductDto product, String uuid) {
        Cart cart = cartRepository.findByUuid(uuid)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));
        List<CartItem> cartItems = cart.getProducts();
        CartItem existingItem = cartItems.stream()
                .filter(cartItem -> cartItem.getProduct().equals(product))
                .findFirst()
                .orElseThrow(() -> new CartNotFoundException("Product not in the cart"));
        if(existingItem.getQuantity() > 1) {
            existingItem.setQuantity(existingItem.getQuantity() - 1);
        } else {
            cartItems.remove(existingItem);
        }
        priceCalculator.calculatePrice(cart);
        cartRepository.save(cart);
        return cartMapper.toCartDto(cart);
    }

    @Override
    @Transactional
    public void clearCart(String uuid) {
        Cart cart = cartRepository.findByUuid(uuid)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));
        cart.getProducts().clear();
        cart.setTotalPrice(0);
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public void deleteCart(String uuid) {
        Cart cart = cartRepository.findByUuid(uuid)
                .orElseThrow(() -> new CartNotFoundException("Cart not found"));
        cartRepository.delete(cart);
    }
}

package com.example.cart_service.rest;

import com.example.cart_service.dto.CartDto;
import com.example.cart_service.dto.ProductDto;
import com.example.cart_service.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class CartRestController {
    private final CartService cartService;

    @GetMapping("/{uuid}")
    public CartDto getCart(@PathVariable String uuid){
        return cartService.getCart(uuid);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public CartDto createCart(){
        return cartService.createCart();
    }

    @PatchMapping("/{uuid}/add")
    public CartDto addToCart(@PathVariable String uuid, @RequestBody ProductDto product){
        return cartService.addToCart(product, uuid);
    }

    @PatchMapping("{uuid}/remove")
    public CartDto removeFromCart(@PathVariable String uuid, @RequestBody ProductDto product){
        return cartService.removeFromCart(product, uuid);
    }

    @PutMapping("{uuid}/clear")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clearCart(@PathVariable String uuid){
        cartService.clearCart(uuid);
    }

    @DeleteMapping("{uuid}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCart(@PathVariable String uuid){
        cartService.deleteCart(uuid);
    }

}

package com.example.cart_service.dto.mapper;

import com.example.cart_service.dto.CartDto;
import com.example.cart_service.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toCartDto(Cart cart);
    Cart toCart(CartDto cartDto);
    void updateCartFromDto(CartDto cartDto,@MappingTarget Cart cart);
}

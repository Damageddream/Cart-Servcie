package com.example.cart_service.repository;

import com.example.cart_service.entity.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CartRepository  extends MongoRepository<Cart,String> {
    Optional<Cart> findByUuid(String uuid);
}

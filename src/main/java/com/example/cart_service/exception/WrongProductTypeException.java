package com.example.cart_service.exception;

public class WrongProductTypeException  extends RuntimeException{
    public WrongProductTypeException(String message) {
        super(message);
    }
}

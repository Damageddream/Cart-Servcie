package com.example.cart_service.exception;

public class RequiredFieldIsNullException extends RuntimeException {
    public RequiredFieldIsNullException(String message) {
        super(message);
    }
}

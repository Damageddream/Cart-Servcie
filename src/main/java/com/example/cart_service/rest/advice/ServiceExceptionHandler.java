package com.example.cart_service.rest.advice;

import com.example.cart_service.exception.RequiredFieldIsNullException;
import com.example.cart_service.exception.WrongProductTypeException;
import com.example.cart_service.exception.CartNotFoundException;
import com.example.cart_service.exception.ProductAlreadyExistsException;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@NoArgsConstructor
public class ServiceExceptionHandler {
    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<ErrorResponse> productNotFoundExceptionHandler(
            CartNotFoundException productNotFoundException) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND,
                productNotFoundException.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> productAlreadyExistsExceptionResponse(
            ProductAlreadyExistsException productAlreadyExistsException) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT,
                productAlreadyExistsException.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
    @ExceptionHandler(RequiredFieldIsNullException.class)
    public ResponseEntity<ErrorResponse> requiredFieldIsNullExceptionHandler(
            RequiredFieldIsNullException requiredFieldIsNullException) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST,
                requiredFieldIsNullException.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(WrongProductTypeException.class)
    public ResponseEntity<ErrorResponse> wrongProductTypeExceptionHandler(
            WrongProductTypeException wrongProductTypeException) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT,
                wrongProductTypeException.getMessage(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleGlobalException(
            Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
package com.example.dio.exception.handler;

import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.SimpleErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestaurantExceptionHandler {
    @ExceptionHandler(UnauthorisedAccessException.class)
    public ResponseEntity<SimpleErrorResponse> handleUnAuthorisedAccess(UnauthorisedAccessException ex){
        return ResponseBuilder.forbidden(ex.getMessage());

    }
}

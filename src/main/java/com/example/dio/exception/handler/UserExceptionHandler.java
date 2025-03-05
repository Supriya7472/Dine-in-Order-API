package com.example.dio.exception.handler;


import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.SimpleErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundByIdException.class)
    public ResponseEntity<SimpleErrorResponse> handleUserNotFoundByIdException(UserNotFoundByIdException ex){
        return ResponseBuilder.notFound(ex.getMessage());
    }
}

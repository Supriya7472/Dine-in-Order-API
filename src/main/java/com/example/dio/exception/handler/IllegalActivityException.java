package com.example.dio.exception.handler;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IllegalActivityException extends RuntimeException {
    private final String message;
}

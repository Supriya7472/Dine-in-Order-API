package com.example.dio.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
    public static <T>ResponseEntity<ResponseStructure<T>> success(HttpStatus status, String message , T data){
        ResponseStructure<T> structure=ResponseStructure.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(status)
                .body(structure);

    }
    public static <T>ResponseEntity<ResponseStructure<T>> ok(String message , T data){
        return success(HttpStatus.OK,message,data);
    }

    public static <T>ResponseEntity<ResponseStructure<T>> created(String message , T data){
        return success(HttpStatus.CREATED,message,data);
    }


    public static <T> ResponseEntity<ResponseStructure<T>> success(HttpStatus status, HttpHeaders headers,String message,T data){
        ResponseStructure<T> structure=ResponseStructure.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(status)
                .headers(headers)
                .body(structure);
    }

    public static ResponseEntity<SimpleErrorResponse> error(HttpStatus status,String message){
        SimpleErrorResponse error=SimpleErrorResponse.builder()
                .type(status.name())
                .status(status.value())
                .message(message)
                .build();

        return ResponseEntity.status(status)
                .body(error);

    }

    public static ResponseEntity<SimpleErrorResponse> notFound(String message){
        return error(HttpStatus.NOT_FOUND,message);
    }
}

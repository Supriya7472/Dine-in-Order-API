package com.example.dio.exception.handler;

import com.example.dio.util.FieldErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class FieldErrorExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<FieldErrorResponse.CustomFieldError> errors = ex.getAllErrors().stream()
                .map(error -> (FieldError) error)
                .map(this::createFieldError)
                .toList();

        FieldErrorResponse error = createFieldErrorResponse(status, errors);

        return ResponseEntity.status(status)
                .body(error);
    }



    private FieldErrorResponse createFieldErrorResponse(HttpStatusCode status, List<FieldErrorResponse.CustomFieldError> errors) {
        FieldErrorResponse error = FieldErrorResponse.builder()
                .type(status.toString())
                .status(status.value())
                .message("Invalid Input")
                .errors(errors)
                .build();
        return error;
    }

    private FieldErrorResponse.CustomFieldError createFieldError(FieldError fieldError) {
        FieldErrorResponse.CustomFieldError error = FieldErrorResponse.createFieldError(
                fieldError.getDefaultMessage(),
                fieldError.getRejectedValue(),
                fieldError.getField());
        return error;
    }
}


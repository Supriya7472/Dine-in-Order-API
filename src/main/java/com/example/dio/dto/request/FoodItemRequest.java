package com.example.dio.dto.request;

import com.example.dio.dto.rules.OnlyAlphabetsAndSpace;
import com.example.dio.enums.DietType;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class FoodItemRequest {


    @OnlyAlphabetsAndSpace
    private String foodName;

    @Positive(message = "Price must be greater than zero")
    private double price;

    @Size(max = 255, message = "Description must be at most 255 characters")
    private String description;

    @Min(value = 0, message = "Stock cannot be negative")
    private int stock;

    @NotNull(message = "Diet type is required")
    private DietType dietType;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;

    @NotNull(message = "cuisine cannot be null")
    private String cuisine;

    @NotNull(message = "At least one category expected")
    private List<String> categories;



}

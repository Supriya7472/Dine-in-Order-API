package com.example.dio.dto.request;

import com.example.dio.dto.rules.Email;
import com.example.dio.dto.rules.OnlyAlphabetsAndSpace;
import com.example.dio.enums.DietType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class RestaurantRequest {

    @OnlyAlphabetsAndSpace
    private String restaurantName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9 ,.#-]+$",message = "Invalid address format")
    private String address;

    @NotNull(message = "Phone number cannot be null")
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[6-9]\\d{9}$",message = "Invalid phone number")
    private String phNo;


    @Email
    private String email;

    @NotNull(message = "Diet type cannot be null")
    @Size(min = 1,message = "At least one diet type expected")
    private List<DietType> dietType;

    @NotNull(message = "Opening time cannot be null")
    private LocalTime opensAt;

    @NotNull(message = "Closing time cannot be null")
    private LocalTime closesAt;


    @FutureOrPresent(message = "Date must be in the present or future")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

    private LocalDateTime createdAt;

    @FutureOrPresent(message = "Date must be in the present or future")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")

    private LocalDateTime lastModifiedAt;

    @NotNull(message = "Cuisine cannot be null")
    @Size(min=1,message = "At least one cuisine is required")
    private List<String> cuisines;
}

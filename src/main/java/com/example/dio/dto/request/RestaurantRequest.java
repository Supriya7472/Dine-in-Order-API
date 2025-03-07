package com.example.dio.dto.request;

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

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+$")
    private String restaurantName;

    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9 ,.#-]+$",message = "Invalid address format")
    private String address;

    @NotNull(message = "Phone number cannot be null")
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[6-9]\\d{9}$",message = "Invalid phone number")
    private String phNo;

    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be blank")
    @Email(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$",message = "Enter valid email id")
    private String email;

    @NotNull(message = "Diet type cannot be null")
    @Size(min = 1,message = "At least one diet type expected")
    private List<DietType> dietType;

    @NotNull(message = "Opening time cannot be null")
//    @Pattern(regexp = "HH:mm", message = "Invalid date format. Use HH:mm")
    @Column(name = "opensAt")
    private LocalTime opensAt;

    @NotNull(message = "Closing time cannot be null")
//    @Pattern(regexp = "HH:mm", message = "Invalid time format. Use HH:mm")
    @Column(name = "closesAt")
    private LocalTime closesAt;

//    @NotNull(message = "Date cannot be null")
    @FutureOrPresent(message = "Date must be in the present or future")
//    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$", message = "Invalid date-time format. Use yyyy-mm-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

//    @NotNull(message = "Date cannot be null")
    @FutureOrPresent(message = "Date must be in the present or future")
//    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$", message = "Invalid date-time format. Use yyyy-mm-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="lastModifiesAt")
    private LocalDateTime lastModifiedAt;

    @NotNull(message = "Cuisine cannot be null")
    @Size(min=1,message = "At least one cuisine is required")
    private List<String> cuisines;
}

package com.example.dio.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    @NotNull(message = "User name cannot be null")
    @NotBlank(message = "User name cannot be blank")
    private String userName;

    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be blank")
    @Email(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$",message = "Enter valid email id")
    private String email;

    @NotNull(message = "Phone number cannot be null")
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[7-9]d{9}$",message = "Invalid phone number")
    private String phNo;

}

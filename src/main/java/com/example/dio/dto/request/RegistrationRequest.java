package com.example.dio.dto.request;

import com.example.dio.enums.UserRole;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {
    @NotNull(message = "User name cannot be null")
    @NotBlank(message = "User name cannot be blank")
    @Pattern(regexp = "^[a-zA-Z0-9_]$",message = "User name can only contain Alphabets,Numbers and Underscore")
    private String userName;

    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be blank")
    @Email(regexp = "^[A-Za-z0-9._%+-]+@gmail\\.com$",message = "Enter valid email id")
    private String email;

    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be blank")
    @Pattern(regexp = "^[A-Za-z\\d@#$%^&+=!]{8,12}$",message = "Password must contain atleast one lower case, upper case, one number,special character,minlength=8 and maxlength=12")
    private String password;

    @NotNull(message = "Phone number cannot be null")
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[7-9]d{9}$",message = "Invalid phone number")
    private String phNo;

    private UserRole role;

}

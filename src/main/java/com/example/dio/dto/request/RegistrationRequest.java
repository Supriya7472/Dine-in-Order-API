package com.example.dio.dto.request;

import com.example.dio.dto.rules.Email;
import com.example.dio.dto.rules.OnlyAlphabetsAndUnderScores;
import com.example.dio.dto.rules.Password;
import com.example.dio.enums.UserRole;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {

    @OnlyAlphabetsAndUnderScores
    private String userName;

    @Email
    private String email;

    @Password
    private String password;

    @NotNull(message = "Phone number cannot be null")
    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^[7-9]\\d{9}$",message = "Invalid phone number")
    private String phNo;

    private UserRole role;

}

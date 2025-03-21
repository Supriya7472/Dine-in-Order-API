package com.example.dio.dto.request;

import com.example.dio.dto.rules.Email;
import com.example.dio.dto.rules.OnlyAlphabetsAndSpace;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    @OnlyAlphabetsAndSpace
    private String userName;

    @Email
    private String email;

    @Pattern(regexp = "^[6-9]\\d{9}$",message = "Invalid phone number")
    private String phNo;

}

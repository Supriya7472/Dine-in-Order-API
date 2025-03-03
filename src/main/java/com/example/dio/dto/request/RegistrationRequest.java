package com.example.dio.dto.request;

import com.example.dio.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationRequest {

    private String userName;

    private String email;

    private String password;

    private String phNo;

    private UserRole role;

}

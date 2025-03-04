package com.example.dio.dto.response;

import com.example.dio.enums.UserRole;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class UserResponse {

    private long userId;

    private String userName;

    private UserRole role;

    private LocalDateTime createdAt;

    private LocalDateTime lastModifiedAt;

}

package com.example.dio.dto.request;

import com.example.dio.enums.UserRole;

public record Auth(Long userId,
                   String username,
                   String email,
                   UserRole role,
                   Long accessExpiration,
                   Long refreshExpiration) {
}

package com.example.dio.security.jwt;

import java.util.Map;

public record TokenPayload(Map<String,Object> claims,
                           long issuedAt,
                           long expiration
                           ) {
}

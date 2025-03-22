package com.example.dio.service.impl;

import com.example.dio.dto.request.Auth;
import com.example.dio.security.jwt.ClaimName;
import com.example.dio.security.jwt.TokenType;
import com.example.dio.service.helper.TokenGenerationServiceHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;


import java.time.Instant;

import java.util.Map;


@Service
@AllArgsConstructor
public class TokenGenerationService {

    private TokenGenerationServiceHelper tokenGenerationServiceHelper;

    public HttpHeaders grantAccessAndRefreshTokens(Auth auth) {

        String accessCookie = tokenGenerationServiceHelper.generateToken(TokenType.ACCESS, claims(auth), Instant.ofEpochMilli(auth.accessExpiration()));
        String refreshCookie = tokenGenerationServiceHelper.generateToken(TokenType.REFRESH, claims(auth), Instant.ofEpochMilli(auth.refreshExpiration()));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.SET_COOKIE,accessCookie);
        httpHeaders.add(HttpHeaders.SET_COOKIE, refreshCookie);

        return httpHeaders;
    }

    private Map<String, Object> claims(Auth auth) {
        return Map.of(
                ClaimName.USER_ID,auth.userId(),
                ClaimName.USER_EMAIL,auth.email(),
                ClaimName.USER_ROLE,auth.role());

    }
}

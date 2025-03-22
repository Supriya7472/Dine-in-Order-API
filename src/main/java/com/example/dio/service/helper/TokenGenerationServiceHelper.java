package com.example.dio.service.helper;

import com.example.dio.config.AppEnv;
import com.example.dio.security.jwt.JWTService;
import com.example.dio.security.jwt.TokenPayload;
import com.example.dio.security.jwt.TokenType;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;

@Component
@AllArgsConstructor
public class TokenGenerationServiceHelper {

    private final JWTService jwtService;
    private final AppEnv env;

    public String generateToken(TokenType tokenType, Map<String,Object> claims, Instant shouldExpireAt){
        TokenPayload payload = this.generateTokenPayload(tokenType, claims, shouldExpireAt);
        String token=jwtService.generateToken(payload);
        return this.generateCookie(tokenType,token, Duration.between(Instant.now(),shouldExpireAt).getSeconds());
    }

    private TokenPayload generateTokenPayload(TokenType tokenType, Map<String,Object> claims, Instant shouldExpireAt ){
        Instant issuedAt = calculateIssuedAt(tokenType, shouldExpireAt);
        return new TokenPayload(claims, issuedAt, shouldExpireAt);

    }

    private Instant calculateIssuedAt(TokenType tokenType, Instant shouldExpireAt) {
        Instant issueAt;
        switch(tokenType){
            case ACCESS -> issueAt= shouldExpireAt.minusSeconds(env.getSecurity().getTokenValidity().getAccessValidity());
            case REFRESH -> issueAt= shouldExpireAt.minusSeconds(env.getSecurity().getTokenValidity().getRefreshValidity());
            default -> throw new IllegalArgumentException("Invalid TokenType passed as argument.");
        }
        return issueAt;
    }

    private String generateCookie(TokenType tokenType,String token,Long maxAge){

       return ResponseCookie.from(tokenType.type(), token)
                .domain(env.getDomain().getName())
                .path("/")
                .sameSite(env.getDomain().getSameSite())
                .httpOnly(true)
                .secure(env.getDomain().isSecure())
                .maxAge(maxAge).build().toString();
    }

}

package com.example.dio.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JWTService {
    private final String secret = "dlLxxNy4d8j9qDnVGddIKYSgJ+TxQUomKF9pIO5H5GU=";
    private final Key key;

    {
        this.key = generateKey();
    }

    public String generateToken(TokenPayload tokenPayload) {
       return Jwts.builder()
                .setClaims(tokenPayload.claims())
                .setIssuedAt(new Date(tokenPayload.issuedAt()))
                .setExpiration(new Date(tokenPayload.expiration()))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    public Key generateKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}



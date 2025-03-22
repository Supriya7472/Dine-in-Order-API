package com.example.dio.security.filters;

import com.example.dio.security.jwt.TokenType;
import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;


public class FilterHelper {

    public static String getToken(TokenType tokenType, Cookie[] cookies){
        String token=null;
        for(Cookie cookie:cookies){
            if (tokenType.type().equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
        return token;
    }
}

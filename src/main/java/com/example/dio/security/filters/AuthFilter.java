package com.example.dio.security.filters;

import com.example.dio.exception.handler.CredentialNotFoundException;
import com.example.dio.exception.handler.InvalidJWTException;
import com.example.dio.security.jwt.ClaimName;
import com.example.dio.security.jwt.JWTService;
import com.example.dio.security.jwt.TokenType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import java.util.List;

@AllArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private final JWTService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();

        String token= FilterHelper.getToken(TokenType.ACCESS,cookies);
        if(token==null || token == ""){
            throw new CredentialNotFoundException("Access token not found in cookies");
        }
        String email=jwtService.parseToken(token).get(ClaimName.USER_EMAIL, String.class);
        String userRole=jwtService.parseToken(token).get(ClaimName.USER_ROLE, String.class);

        if(email==null || email=="" || userRole==null|| userRole==""){
            throw new InvalidJWTException("Claims not found");
        }

        if(SecurityContextHolder.getContext().getAuthentication()==null){
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new
                    UsernamePasswordAuthenticationToken(email,null,List.of(new SimpleGrantedAuthority(userRole)));
            usernamePasswordAuthenticationToken.setDetails(request);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request,response);
    }

}

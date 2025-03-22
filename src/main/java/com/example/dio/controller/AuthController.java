package com.example.dio.controller;

import com.example.dio.dto.request.Auth;
import com.example.dio.dto.request.LoginRequest;
import com.example.dio.service.AuthService;
import com.example.dio.service.impl.TokenGenerationService;

import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class AuthController {
    private final AuthService authService;
    private final TokenGenerationService tokenGenerationService;

    @PostMapping("/login")
    public ResponseEntity<ResponseStructure<Auth>> login(@RequestBody LoginRequest loginRequest){
        Auth auth=authService.login(loginRequest);
        HttpHeaders httpHeaders =tokenGenerationService.grantAccessAndRefreshTokens(auth);
        return ResponseBuilder.success(HttpStatus.OK, httpHeaders, "Login Successful", auth);
    }
}

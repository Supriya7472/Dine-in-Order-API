package com.example.dio.service;

import com.example.dio.dto.request.Auth;
import com.example.dio.dto.request.LoginRequest;

public interface AuthService {
    Auth login(LoginRequest loginRequest);
}

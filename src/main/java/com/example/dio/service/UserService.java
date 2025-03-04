package com.example.dio.service;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;

public interface UserService {
    public UserResponse registerUser(RegistrationRequest registrationRequest);

    public UserResponse findUserById(long userId);

    public UserResponse updateUserById(UserRequest userRequest, long userId);
}

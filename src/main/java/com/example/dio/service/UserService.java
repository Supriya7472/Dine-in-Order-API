package com.example.dio.service;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;

public interface UserService {

    /**
     * This service is registers new users
     *
     * @param  registrationRequest  Specifies registrationRequest object of type RegistrationRequest
     *
     * @return UserResponse
     * */
    public UserResponse registerUser(RegistrationRequest registrationRequest);

    /**
     * This service fetches user data using user id
     *
//     * @param userEmail specifies user id
     *
     * @return UserResponse
     * */
    public UserResponse findUserByEmail();

    /**
     * this service updates existing users
     *

     * @param userRequest specifies userRequest of type UserRequest
     * */
    public UserResponse updateUserByEmail(UserRequest userRequest);
}

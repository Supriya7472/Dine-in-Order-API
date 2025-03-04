package com.example.dio.service.impl;

import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.enums.UserRole;
import com.example.dio.exception.handler.UserNotFoundByIdException;
import com.example.dio.mapper.UserMapper;
import com.example.dio.model.Admin;
import com.example.dio.model.Staff;
import com.example.dio.model.User;
import com.example.dio.repository.UserRepository;
import com.example.dio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserResponse registerUser(RegistrationRequest registrationRequest){
        User child=this.createUserByRole(registrationRequest.getRole());
        userMapper.mapToNewUser(registrationRequest,child);
        User user=userRepository.save(child);
        return userMapper.mapToUserResponse(user);
    }





    private User createUserByRole(UserRole role) {
        User user;
        switch (role){
            case ADMIN -> user=new Admin();
            case STAFF -> user=new Staff();
            default -> throw new RuntimeException("Failed registration user,invalid User");
        }
       return user;
    }




    @Override
    public UserResponse findUserById(long userId) {
//        Optional<User> user=userRepository.findById(userId);
//        return user.orElseThrow(()->new UserNotFoundByIdException("User Not Found"));
        return userRepository.findById(userId)
                .map(userMapper::mapToUserResponse)
                .orElseThrow(()->new UserNotFoundByIdException("Failed to find user, User not found by id"));

    }


    @Override
    public UserResponse updateUserById(UserRequest userRequest, long userId) {
//        Optional<User> exUser=userRepository.findById(userId);
//        if(exUser.isPresent()){
//            this.mapToNewUser(user,exUser.get());
//            return userRepository.save(user);
//        }
//        throw new UserNotFoundByIdException("Update failed, User not found");
        User exUser=userRepository.findById(userId)
                .orElseThrow(()->new UserNotFoundByIdException("Failed to update user, user not found by Id"));
        userMapper.mapToNewUser(userRequest,exUser);

        return userMapper.mapToUserResponse(userRepository.save(exUser));
    }
}

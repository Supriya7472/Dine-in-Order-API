package com.example.dio.mapper;

import ch.qos.logback.core.model.ComponentModel;
import com.example.dio.dto.request.RegistrationRequest;
import com.example.dio.dto.request.UserRequest;
import com.example.dio.dto.response.UserResponse;
import com.example.dio.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel ="spring")
public interface UserMapper {
//    public UserResponse mapToUserResponse(User user) {
//        return UserResponse.builder()
//                .userId(user.getUserId())
//                .userName(user.getUserName())
//                .role(user.getRole())
//                .createdAt(user.getCreatedAt())
//                .lastModifiedAt(user.getLastModifiedAt())
//                .build();
//    }

//    public void mapToNewUser(RegistrationRequest registrationRequest, User child) {
//        child.setUserName(registrationRequest.getUserName());
//        child.setPhNo(registrationRequest.getPhNo());
//        child.setEmail(registrationRequest.getEmail());
//        child.setRole(registrationRequest.getRole());
//        child.setPassword(registrationRequest.getPassword());
//    }




//    public void mapToNewUser(UserRequest source, User target) {
//        target.setUserName(source.getUserName());
//        target.setEmail(source.getEmail());
//        target.setPhNo(source.getPhNo());
//    }
    public UserResponse mapToUserResponse(User user);
    public void mapToNewUser(RegistrationRequest registrationRequest, @MappingTarget User child);
    public void mapToNewUser(UserRequest source,@MappingTarget User target);
}

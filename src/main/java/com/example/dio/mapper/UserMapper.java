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

    /**
     * Creating an abstract method to map User data to UserResponse data
     * where implementation is automatically provided by MapStruct
     * */
    public UserResponse mapToUserResponse(User user);

    /**
     * Creating an abstract method to map User data to NewUser data while updation operation
     * where implementation is automatically provided by MapStruct
     * */
    public void mapToNewUser(RegistrationRequest registrationRequest, @MappingTarget User child);


    /**
     * Creating an abstract method to map old User data to new UserResponse data
     * where implementation is automatically provided by MapStruct
     * */
    public void mapToNewUser(UserRequest source,@MappingTarget User target);
}

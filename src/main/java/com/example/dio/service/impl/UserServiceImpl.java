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
import com.example.dio.security.util.UserIdentity;
import com.example.dio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserIdentity userIdentity;

    public UserResponse registerUser(RegistrationRequest registrationRequest){
        User child=this.createUserByRole(registrationRequest.getRole());
        userMapper.mapToNewUser(registrationRequest,child);
        this.encryptPassword(child);
        userRepository.save(child);
        return userMapper.mapToUserResponse(child);
    }

    private void encryptPassword(User user){
        String encodedPassword=passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }


    /**
     * Produces and returns child instance of the User based on the User role.
     *
     * @param role the role of the user
     *
     * @return User, the parent reference containing either staff or Admin instance
     * */
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
    public UserResponse findUserByEmail() {

        User user =userIdentity.getCurrentUser();
        return userMapper.mapToUserResponse(user);

    }


    @Override
    public UserResponse updateUserByEmail(UserRequest userRequest) {

        User exUser=userIdentity.getCurrentUser();
        userIdentity.validateOwnerShip(exUser.getEmail());
        userMapper.mapToNewUser(userRequest,exUser);
        userRepository.save(exUser);
        return userMapper.mapToUserResponse(exUser);
    }
}

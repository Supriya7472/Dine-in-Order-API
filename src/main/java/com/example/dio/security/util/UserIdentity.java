package com.example.dio.security.util;

import com.example.dio.exception.handler.IllegalActivityException;
import com.example.dio.model.User;
import com.example.dio.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserIdentity {

    private final UserRepository userRepository;

    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getCurrentUserEmail(){
        return this.getAuthentication().getName();
    }

    public User getCurrentUser(){
         return userRepository.findByEmail(this.getCurrentUserEmail())
                .orElseThrow(()-> new RuntimeException("Failed to fetch user"));
    }

    public void validateOwnerShip(String ownername){
        if(!this.getCurrentUserEmail().equals(ownername)){
            throw new IllegalActivityException("User not allowed to access or modify the resource requested");
        }

    }
}

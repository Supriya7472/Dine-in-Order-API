package com.example.dio.service.impl;

import com.example.dio.dto.request.Auth;
import com.example.dio.dto.request.LoginRequest;
import com.example.dio.model.User;
import com.example.dio.repository.UserRepository;
import com.example.dio.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    @Override
    public Auth login(LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password());
        Authentication authentication = authenticationManager.authenticate(token);
        Optional<User> user;
        if (authentication.isAuthenticated()) {
            user = userRepository.findByEmail(loginRequest.email());
            return generateAuth(user);

        } else
            throw new UsernameNotFoundException("Failed to Authenticate.");

    }

    private static Auth generateAuth(Optional<User> user) {
        Instant now=Instant.now();
        Long accessExpiration=now.plusSeconds(3600).toEpochMilli();
        Long refreshExpiration=now.plusSeconds(129600).toEpochMilli();
        Auth auth = new Auth(user.get().getUserId(),
                user.get().getUserName(),
                user.get().getEmail(),
                user.get().getRole(),
                accessExpiration,
                refreshExpiration);
        return auth;
    }
}

package com.example.dio.config;

import com.example.dio.security.util.UserIdentity;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;


import java.util.Optional;

@Configuration
@AllArgsConstructor
public class AuditorAwareImpl implements AuditorAware {
    private final UserIdentity userIdentity;
    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            return Optional.ofNullable(userIdentity.getCurrentUserEmail());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}

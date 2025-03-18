package com.example.dio.config;

import com.cloudinary.Cloudinary;

import com.cloudinary.utils.ObjectUtils;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@AllArgsConstructor
public class CloudinaryConfig {
    private final AppEnv env;

    @Bean
    Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", env.getCloudinary().getCloudName(),
                "api_key", env.getCloudinary().getApiKey(),
                "api_secret", env.getCloudinary().getApiSecret()
        ));
    }
}

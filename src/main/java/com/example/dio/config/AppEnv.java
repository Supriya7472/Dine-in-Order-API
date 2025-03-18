package com.example.dio.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app")
@Component
@Getter
@Setter
public class AppEnv {
    private String baseUrl;
    private Cloudinary cloudinary;

    @Getter
    @Setter
    public static class Cloudinary{
        private String apiKey;
        private String apiSecret;
        private String cloudName;
    }
}

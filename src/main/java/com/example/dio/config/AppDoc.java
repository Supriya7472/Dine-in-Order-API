package com.example.dio.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class AppDoc {

    @Bean
    OpenAPI openAPI(){
        return new OpenAPI().info(info()
        );

    }

    private Info info() {
        return new Info()
                .title("Dine In Order API")
                .description("""
                        ## Description
                        **Dine In Order** is an API built using Spring boot under REST Architecture
                        The API is designed to serve as a backend to an application that deals with order food online
                        
                        ## Tech-Stack
                        - Java 8
                        - Spring Boot
                        - Spring Data JPA
                        - MySQL Database
                        - Spring Security
                        
                        
                        """)
                .version("v1")
                .contact(contact());
    }

    private Contact contact() {
        return new Contact().email("supriya@gmail.com")
                .name("Supriya")
                .url("https://google.com");
    }
}

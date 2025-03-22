package com.example.dio.security.config;

import com.example.dio.config.AppEnv;
import com.example.dio.security.filters.AuthFilter;
import com.example.dio.security.jwt.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {
    private final AppEnv env;
    private final JWTService jwtService;
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        String baseUrl=env.getBaseUrl();
        return http.csrf(csrf->csrf.disable())
                .securityMatchers(match-> match.requestMatchers(baseUrl+"/**","/login/**","/logout/**")) //basically used to configure
                                                                                       //filter chain to accept request made to a specific
                                                                                       //pattern default="/**"
                .authorizeHttpRequests(authorize->
                        authorize.requestMatchers(
                                baseUrl+"/register",
                                baseUrl+"/restaurants/{restaurantId}/foodItems",
                                baseUrl+"/login"

                        ).permitAll()
//                                .requestMatchers(baseUrl+"/restaurants/{userId}")
//                                .hasAuthority("ADMIN")
                                .anyRequest()
                                .authenticated())
                .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new AuthFilter(jwtService), UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
         return configuration.getAuthenticationManager();
    }

}

package com.example.dio.module;

import com.example.dio.enums.UserRole;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private long userId;

    @Column(name="username")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "ph-no")
    private String phNo;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="last_modified_at")
    private LocalDateTime lastModifiedAt;



}

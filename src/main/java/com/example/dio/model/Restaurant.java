package com.example.dio.model;

import com.example.dio.enums.DietType;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
public class Restaurant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "restaurantId")
    private long restaurantId;



    @Column(name="restaurantName")
    private String restaurantName;

    @Column(name = "address")
    private String address;


    @Column(name="phNo")
    private String phNo;

    @Column(name="email")
    private String email;

    @Column(name = "opensAt")
    private LocalTime opensAt;

    @Column(name = "closesAt")
    private LocalTime closesAt;

    @Column(name="dietTYpe")
    @Enumerated(EnumType.STRING)
    private List<DietType> dietType;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @Column(name="lastModifiesAt")
    private LocalDateTime lastModifiedAt;

    @ManyToMany
    private List<Cuisine> cuisines;

    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;


}

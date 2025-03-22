package com.example.dio.model;

import com.example.dio.enums.DietType;
import jakarta.persistence.*;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name="lastModifiesAt")
    private LocalDateTime lastModifiedAt;

    @CreatedBy
    @Column(name = "createdBy")
    private String createdBy;

    @ManyToMany
    private List<Cuisine> cuisines;

    @ManyToOne(fetch = FetchType.LAZY)
    private Admin admin;

    @OneToMany(mappedBy = "restaurant",fetch = FetchType.LAZY)
    private List<RestaurantTable> table;

}

package com.example.dio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cuisines")
@Getter
@Setter
public class Cuisine {
    @Id
    @Column(name = "cuisine")
    private String cuisine;

    @ManyToMany
    private List<Restaurant> restaurants;
}

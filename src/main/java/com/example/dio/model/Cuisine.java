package com.example.dio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cuisines")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public class Cuisine {
    @Id
    @Column(name = "cuisine")
    private String cuisine;

    @ManyToMany
    private Restaurant restaurant;
}

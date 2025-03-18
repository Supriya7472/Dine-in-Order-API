package com.example.dio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "imageId")
    private long imageId;

    @Column(name = "imageUrl")
    private String imageUrl;

    @ManyToOne
    private FoodItem foodItem;

}

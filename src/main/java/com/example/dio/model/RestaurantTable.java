package com.example.dio.model;

import com.example.dio.enums.RestaurantTableStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tables")
@Getter
@Setter

public class RestaurantTable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tableId")
    private long tableId;

    @Column(name = "tableNo")
    private int tableNo;

    @Column(name = "tableCapacity")
    private int tableCapacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "tableStatus")
    private RestaurantTableStatus tableStatus;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany(mappedBy = "restaurantTable")
    private List<CartItem> cartItems;

}

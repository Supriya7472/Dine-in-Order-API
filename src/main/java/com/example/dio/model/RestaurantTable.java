package com.example.dio.model;

import com.example.dio.enums.RestaurantTableStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Table(name = "restaurant_tables")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedBy
    @Column(name = "createdBy")
    private String createdBy;

    @ManyToOne
    private Restaurant restaurant;

    @OneToMany(mappedBy = "restaurantTable",fetch = FetchType.LAZY)
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "table",fetch = FetchType.LAZY)
    private List<TableOrder> orders;

}

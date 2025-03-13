package com.example.dio.model;

import com.example.dio.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tableOrders")
public class TableOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "orderStatus")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @CreatedDate
    @Column(name = "orderedAt")
    private LocalTime orderedAt;

    @Column(name = "totalAmount")
    private double totalAmount;

    @OneToMany
    private List<CartItem> cartItems;

    @ManyToOne
    private RestaurantTable table;
}

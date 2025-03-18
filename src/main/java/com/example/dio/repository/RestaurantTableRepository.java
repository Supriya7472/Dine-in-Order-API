package com.example.dio.repository;

import com.example.dio.model.RestaurantTable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTableRepository extends JpaRepository<RestaurantTable,Long> {

    @Query("SELECT MAX(t.tableNo) FROM RestaurantTable t WHERE t.restaurant.restaurantId = :restaurantId")
    Integer findMaxTableNoByRestaurant(@Param("restaurantId") Long restaurantId);
}

package com.example.dio.service.impl;

import com.example.dio.dto.request.RestaurantTableRequest;

import com.example.dio.dto.response.RestaurantTableResponse;
import com.example.dio.mapper.RestaurantTableMapper;

import com.example.dio.model.Restaurant;
import com.example.dio.model.RestaurantTable;

import com.example.dio.model.User;
import com.example.dio.repository.RestaurantRepository;

import com.example.dio.repository.RestaurantTableRepository;
import com.example.dio.repository.UserRepository;
import com.example.dio.service.RestaurantTableService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service

@AllArgsConstructor
public class TableServiceImpl implements RestaurantTableService {
    private final RestaurantTableRepository tableRepository;
    private final RestaurantRepository restaurantRepository;

    private final RestaurantTableMapper tableMapper;


    @Override
    public RestaurantTableResponse createTable(Long restaurantId, RestaurantTableRequest restaurantTableRequest) {



        // Fetch the restaurant
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + restaurantId));

        RestaurantTable table= tableMapper.mapToTable(restaurantTableRequest);
        // Set restaurant
        table.setRestaurant(restaurant);

        // Set tableNo (logic inside service class)
        table.setTableNo(generateNextTableNo(restaurantId));

        // Save and return the table
        tableRepository.save(table);
        return tableMapper.mapToTableResponse(table);
    }

    // Method to generate the next table number
    private int generateNextTableNo(Long restaurantId) {
        Integer maxTableNo = tableRepository.findMaxTableNoByRestaurant(restaurantId);
        return (maxTableNo == null) ? 1 : maxTableNo + 1;
    }


}

package com.example.dio.service;

import com.example.dio.dto.request.RestaurantTableRequest;

import com.example.dio.dto.response.RestaurantTableResponse;
import com.example.dio.model.RestaurantTable;


public interface RestaurantTableService {
    RestaurantTableResponse createTable(Long restaurantId, RestaurantTableRequest restaurantTableRequest);

    RestaurantTableResponse getTableById(Long tableId);

    RestaurantTableResponse updateTableById(Long tableId,RestaurantTableRequest restaurantTableRequest);
}

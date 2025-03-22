package com.example.dio.service;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.util.ResponseStructure;
import org.springframework.http.ResponseEntity;

public interface RestaurantService {
    RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest,long userId);

    RestaurantResponse findRestaurantById(Long restaurantId);

    RestaurantResponse updateRestaurantById(Long restaurantId, RestaurantRequest restaurantRequest);
}

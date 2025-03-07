package com.example.dio.controller;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.service.RestaurantService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class RestaurantController {

    private RestaurantService restaurantService;

    @PostMapping("/restaurants/{userId}")
    public ResponseEntity<ResponseStructure<RestaurantResponse>> createRestaurant(
            @PathVariable long userId,
            @Valid @RequestBody RestaurantRequest restaurantRequest) {

        RestaurantResponse restaurantResponse = restaurantService.createRestaurant(restaurantRequest, userId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseStructure.create(HttpStatus.CREATED, "Restaurant created successfully", restaurantResponse));
    }
}

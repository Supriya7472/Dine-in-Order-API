package com.example.dio.controller;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.model.Restaurant;
import com.example.dio.service.RestaurantService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class RestaurantController {

    private RestaurantService restaurantService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("users/{userId}/restaurants")
    public ResponseEntity<ResponseStructure<RestaurantResponse>> createRestaurant(
            @PathVariable long userId,
            @Valid @RequestBody RestaurantRequest restaurantRequest) {

        RestaurantResponse restaurantResponse = restaurantService.createRestaurant(restaurantRequest, userId);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseStructure.create(HttpStatus.CREATED, "Restaurant created successfully", restaurantResponse));
    }

    @GetMapping("/restaurants/{restaurantId}")
    public ResponseEntity<ResponseStructure<RestaurantResponse>> findRestaurantById(@PathVariable Long restaurantId){
        RestaurantResponse restaurantResponse=restaurantService.findRestaurantById(restaurantId);
        return ResponseBuilder.found("Restaurant not found with id :"+restaurantId,restaurantResponse);
    }

    @PutMapping("/restaurants/{restaurantId}")
    public ResponseEntity<ResponseStructure<RestaurantResponse>> updateRestaurantById(@PathVariable Long restaurantId,@RequestBody RestaurantRequest restaurantRequest){
        RestaurantResponse restaurantResponse=restaurantService.updateRestaurantById(restaurantId,restaurantRequest);
        return ResponseBuilder.ok("Restaurant updated!! :"+restaurantId,restaurantResponse);
    }


}

package com.example.dio.controller;

import com.example.dio.dto.request.RestaurantTableRequest;

import com.example.dio.dto.response.RestaurantTableResponse;
import com.example.dio.model.RestaurantTable;

import com.example.dio.service.RestaurantTableService;
import com.example.dio.util.ResponseStructure;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Getter
@Setter
@RequestMapping("${app.base-url}")
public class TableController {

    @Autowired
    private RestaurantTableService tableService;

    @PostMapping("/tables/restaurants/{restaurantId}")
    public ResponseEntity<ResponseStructure<RestaurantTableResponse>> createTable(
                                                                                @PathVariable Long restaurantId,
                                                                                @RequestBody RestaurantTableRequest restaurantTableRequest) {

        RestaurantTableResponse table = tableService.createTable(restaurantId, restaurantTableRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseStructure.create(HttpStatus.CREATED, "Restaurant created successfully",table));
    }
}

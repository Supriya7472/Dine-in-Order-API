package com.example.dio.controller;

import com.example.dio.dto.request.RestaurantTableRequest;

import com.example.dio.dto.response.RestaurantTableResponse;
import com.example.dio.model.RestaurantTable;

import com.example.dio.security.util.UserIdentity;
import com.example.dio.service.RestaurantTableService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class TableController {


    private final RestaurantTableService tableService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/tables/restaurants/{restaurantId}")
    public ResponseEntity<ResponseStructure<RestaurantTableResponse>> createTable(
                                                                                @PathVariable Long restaurantId,
                                                                                @RequestBody RestaurantTableRequest restaurantTableRequest) {

        RestaurantTableResponse table = tableService.createTable(restaurantId, restaurantTableRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseStructure.create(HttpStatus.CREATED, "Restaurant created successfully",table));
    }

    @GetMapping("/tables/{tableId}")
    public ResponseEntity<ResponseStructure<RestaurantTableResponse>> getTableById(@PathVariable Long tableId){
        RestaurantTableResponse tableResponse=tableService.getTableById(tableId);
        return ResponseBuilder.found("Table Found",tableResponse);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/tables/{tableId}")
    public ResponseEntity<ResponseStructure<RestaurantTableResponse>> updateTableById(@PathVariable Long tableId,@RequestBody RestaurantTableRequest tableRequest){
        RestaurantTableResponse tableResponse=tableService.updateTableById(tableId,tableRequest);
        return ResponseBuilder.ok("Table Updated",tableResponse);
    }

}

package com.example.dio.controller;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.service.FoodItemService;
import com.example.dio.util.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class FoodItemController {
    @Autowired
    private FoodItemService foodItemService;

    @PostMapping("/foodItems/restaurants/{restaurantId}")
    public ResponseEntity<ResponseStructure<FoodItemResponse>> createFoodItem(@PathVariable Long restaurantId, @RequestBody FoodItemRequest foodItemRequest){
        FoodItemResponse foodItemResponse=foodItemService.createFoodItem(restaurantId,foodItemRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ResponseStructure.create(HttpStatus.CREATED,"Food item created",foodItemResponse));
    }

    @GetMapping("/foodItems/categories")
    public ResponseEntity<ResponseStructure<List<FoodItemResponse>>> getFoodItemsByCategories(
            @RequestParam("category") List<String> categories) {

        List<FoodItemResponse> foodItemResponses = foodItemService.getFoodItemsByCategories(categories);

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(ResponseStructure.create(HttpStatus.FOUND, "Food items found", foodItemResponses));
    }


}

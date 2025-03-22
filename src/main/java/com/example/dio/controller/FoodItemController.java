package com.example.dio.controller;

import com.example.dio.dto.request.FoodItemRequest;
import com.example.dio.dto.response.FoodItemResponse;
import com.example.dio.model.FoodItem;
import com.example.dio.service.FoodItemService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructure;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class FoodItemController {
    @Autowired
    private FoodItemService foodItemService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("restaurants/{restaurantId}/foodItems")
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

    @GetMapping("restaurants/{restaurantId}/foodItems")
    public ResponseEntity<ResponseStructure<List<FoodItemResponse>>> getAllFoodItems(
            @PathVariable Long restaurantId){
        List<FoodItemResponse> foodItemResponses=foodItemService.getAllFoodItems(restaurantId);

        return ResponseEntity.status(HttpStatus.FOUND)
                .body(ResponseStructure.create(HttpStatus.FOUND,"Food items found in restaurant",foodItemResponses));
    }

    @GetMapping("foodItems/{itemId}")
    public ResponseEntity<ResponseStructure<FoodItemResponse>> getFoodItemById(@PathVariable Long itemId){
        FoodItemResponse foodItemResponse=foodItemService.getFoodItemById(itemId);
        return ResponseBuilder.found("Food item found with id : "+itemId,foodItemResponse);

    }

    @PutMapping("foodItems/{itemId}")
    public ResponseEntity<ResponseStructure<FoodItemResponse>> updateFoodItemById(@PathVariable Long itemId,@RequestBody FoodItemRequest foodItemRequest){
        FoodItemResponse foodItemResponse=foodItemService.updateFoodItemById(itemId,foodItemRequest);
        return ResponseBuilder.ok("Food item updated.",foodItemResponse);
    }



}

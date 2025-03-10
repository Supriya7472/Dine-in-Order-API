package com.example.dio.dto.request;

import com.example.dio.enums.RestaurantTableStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantTableRequest {

    private int tableCapacity;

    private RestaurantTableStatus tableStatus;
}

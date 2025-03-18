package com.example.dio.dto.request;

import com.example.dio.enums.RestaurantTableStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantTableRequest {

    @NotNull(message = "table capacity cannot be null")
    private int tableCapacity;

    private RestaurantTableStatus tableStatus;
}

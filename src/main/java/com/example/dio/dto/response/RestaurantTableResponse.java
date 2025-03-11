package com.example.dio.dto.response;

import com.example.dio.enums.RestaurantTableStatus;
import com.example.dio.model.Restaurant;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RestaurantTableResponse {
    private int tableNo;

    private int tableCapacity;

    private RestaurantTableStatus tableStatus;


}

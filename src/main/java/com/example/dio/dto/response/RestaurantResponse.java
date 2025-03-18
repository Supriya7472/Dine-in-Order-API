package com.example.dio.dto.response;

import com.example.dio.enums.DietType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Setter
@Getter
@Builder
public class RestaurantResponse {
    private long restaurantId;

    private String restaurantName;

    private String address;

    private String phNo;

    private String email;

    private LocalTime opensAt;

    private LocalTime closesAt;

    private List<DietType> dietType;

    private List<String> cuisines;
}

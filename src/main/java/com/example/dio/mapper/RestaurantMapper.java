package com.example.dio.mapper;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.model.Cuisine;
import com.example.dio.model.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel ="spring")
public interface RestaurantMapper {

    /**
     * Helps with mapping RestaurantRequest to Restaurant type
     *
     * @param restaurantRequest of type RestaurantRequest
     *
     * @return Restaurant type
     * */
    Restaurant mapToRestaurant(RestaurantRequest restaurantRequest);

    /**
     * Helps with mapping Restaurant to RestaurantResponse
     *
     * @param restaurant of type Restaurant
     * @return RestaurantResponse
     * */
    RestaurantResponse mapToRestaurantResponse(Restaurant restaurant);


    /**
     * Helps with mapping cuisine type data to String
     *
     * @param cuisine of type Cuisine
     * @return String
     * */
    default String mapToStringCuisineType(Cuisine cuisine){
        if(cuisine ==null){
            return null;
        }
        return cuisine.getCuisine();
    }

    /**
     * Helps with mapping String type cuisine to cuisine object
     *
     * @param cuisine
     * @return Cuisine type object
     * */
    default Cuisine mapToCuisineType(String cuisine){
        if(cuisine==null){
            return null;
        }
        Cuisine cuisine1=new Cuisine();
        cuisine1.setCuisine(cuisine);
        return cuisine1;
    }

}

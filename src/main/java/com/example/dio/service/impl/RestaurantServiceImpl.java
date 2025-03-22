package com.example.dio.service.impl;

import com.example.dio.dto.request.RestaurantRequest;
import com.example.dio.dto.response.RestaurantResponse;
import com.example.dio.exception.handler.UnauthorisedAccessException;
import com.example.dio.exception.handler.UserNotFoundByIdException;
import com.example.dio.mapper.RestaurantMapper;
import com.example.dio.model.Admin;
import com.example.dio.model.Cuisine;
import com.example.dio.model.Restaurant;
import com.example.dio.model.User;
import com.example.dio.repository.CuisineRepository;
import com.example.dio.repository.RestaurantRepository;
import com.example.dio.repository.UserRepository;
import com.example.dio.security.util.UserIdentity;
import com.example.dio.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@AllArgsConstructor
@Transactional
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;
    private final UserRepository userRepository;
    private final CuisineRepository cuisineRepository;
    private final UserIdentity userIdentity;


    @Override
    public RestaurantResponse createRestaurant(RestaurantRequest restaurantRequest, long userId) {
        // Verify user existence
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException("User with ID " + userId + " not found"));

        // Ensure only Admins can create a restaurant
        if (!(user instanceof Admin admin)) {
            throw new UnauthorisedAccessException("Only Admin is allowed to create a restaurant");
        }

        // Map DTO to entity
        Restaurant restaurant = restaurantMapper.mapToRestaurant(restaurantRequest);
        restaurant.setAdmin(admin);

        List<Cuisine> existingList=restaurant.getCuisines();
        List<Cuisine> newList=createNonExistingCuisine(existingList);
        restaurant.setCuisines(newList);

        restaurantRepository.save(restaurant);

        // Map to response
        return restaurantMapper.mapToRestaurantResponse(restaurant);
    }

    @Override
    public RestaurantResponse findRestaurantById(Long restaurantId) {
        Restaurant restaurant=restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new RuntimeException("Restaurant not found with id:"+restaurantId));
        return restaurantMapper.mapToRestaurantResponse(restaurant);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public RestaurantResponse updateRestaurantById(Long restaurantId, RestaurantRequest restaurantRequest) {
        Restaurant exRestaurant =restaurantRepository.findById(restaurantId)
                .orElseThrow(()-> new RuntimeException("Restaurant not found with id:"+restaurantId));
        userIdentity.validateOwnerShip(exRestaurant.getCreatedBy());
        restaurantMapper.mapToNewRestaurant(restaurantRequest,exRestaurant);
        restaurantRepository.save(exRestaurant);
        return restaurantMapper.mapToRestaurantResponse(exRestaurant);
    }

    private List<Cuisine> createNonExistingCuisine(List<Cuisine> cuisineType){
        return cuisineType.stream()
                .map(existingCuisine ->
                        cuisineRepository.findById(existingCuisine.getCuisine())
                                .orElseGet(()-> cuisineRepository.save(existingCuisine)))
                .toList();
    }
}

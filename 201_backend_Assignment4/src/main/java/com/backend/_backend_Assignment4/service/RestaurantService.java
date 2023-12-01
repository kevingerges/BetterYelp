package com.backend._backend_Assignment4.service;


import com.backend._backend_Assignment4.dto.RestaurantDTO;
import com.backend._backend_Assignment4.model.Restaurant;
import com.backend._backend_Assignment4.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend._backend_Assignment4.service.RestaurantService;


@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant addRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDTO.getName());
        restaurant.setLatitude(restaurantDTO.getLatitude());
        restaurant.setLongitude(restaurantDTO.getLongitude());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setPhone(restaurantDTO.getPhone());
        restaurant.setPrice(restaurantDTO.getPrice());
        restaurant.setCuisine(restaurantDTO.getCuisine());
        restaurant.setRating(restaurantDTO.getRating());
        restaurant.setReviewCount(restaurantDTO.getReviewCount());
        restaurant.setUrl(restaurantDTO.getUrl());
        restaurant.setImageUrl(restaurantDTO.getImageUrl());
        return restaurantRepository.save(restaurant);
    }
}
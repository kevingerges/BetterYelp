package com.backend._backend_Assignment4.service;


import com.backend._backend_Assignment4.dto.RestaurantDTO;
import com.backend._backend_Assignment4.model.Restaurant;
import com.backend._backend_Assignment4.util.YelpAPIUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class YelpAPIService {

    @Autowired
    private YelpAPIUtil yelpAPIUtil;

    public YelpAPIService(YelpAPIUtil yelpAPIUtil) {
        this.yelpAPIUtil = yelpAPIUtil;
    }

    public List<RestaurantDTO> searchRestaurants(String name, double latitude, double longitude, String filter) throws IOException {
        // Call YelpAPIUtil to perform the actual search
        List<Restaurant> restaurants = yelpAPIUtil.searchRestaurants(name, latitude, longitude, filter);

        // Convert the list of Restaurant objects to RestaurantDTOs
        List<RestaurantDTO> restaurantDTOs = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            RestaurantDTO Restaurantdto = convertToDTO(restaurant);
            restaurantDTOs.add(Restaurantdto);
        }

        return restaurantDTOs;
    }

    private RestaurantDTO convertToDTO(Restaurant restaurant) {
//        System.out.println(  restaurant.getName() + " " +
//                restaurant.getLatitude() + " " +
//                restaurant.getLongitude() + " " +
//                restaurant.getAddress()+ " " +
//                restaurant.getRating()+ " " +
//                restaurant.getReviewCount()+ " " +
//                restaurant.getImageUrl()+ " " +
//                restaurant.getUrl()+ " " +
//                restaurant.getPhone()+ " " +
//                restaurant.getCuisine()+ " " +
//                restaurant.getPrice());
        return new RestaurantDTO(
                restaurant.getName(),
                restaurant.getLatitude(),
                restaurant.getLongitude(),
                restaurant.getAddress(),
                restaurant.getRating(),
                restaurant.getReviewCount(),
                restaurant.getImageUrl(),
                restaurant.getUrl(),
                restaurant.getPhone(),
                restaurant.getCuisine(),
                restaurant.getPrice()
        );
    }
}

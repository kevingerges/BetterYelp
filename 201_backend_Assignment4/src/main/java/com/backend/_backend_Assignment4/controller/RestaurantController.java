package com.backend._backend_Assignment4.controller;

import com.backend._backend_Assignment4.dto.RestaurantDTO;
import com.backend._backend_Assignment4.model.Restaurant;

import com.backend._backend_Assignment4.repository.RestaurantRepository;
import com.backend._backend_Assignment4.service.RestaurantService;
import com.backend._backend_Assignment4.service.YelpAPIService;

import com.backend._backend_Assignment4.service.RestaurantService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5500")
public class RestaurantController {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    @Autowired
    private YelpAPIService yelpAPIService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @PostMapping("/restaurants/add")
    public ResponseEntity<?> addRestaurant(@RequestBody RestaurantDTO restaurantDTO) {

        Restaurant restaurant = restaurantService.addRestaurant(restaurantDTO);

        logger.info("Received restaurant data: {}", restaurantDTO);

        // Logic to save restaurant details
        return ResponseEntity.ok().body("{\"status\":\"success\"}");
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchRestaurants(
            @RequestParam String name,
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam String filter) {
        try {
            List<RestaurantDTO> results = yelpAPIService.searchRestaurants(name, latitude, longitude, filter);
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error during search: " + e.getMessage());
        }
    }

    @GetMapping("/restaurants/id")
    public ResponseEntity<?> getRestaurantIdByName(@RequestParam String name) {
        Restaurant restaurant = restaurantRepository.findByName(name);
        if (restaurant != null) {
            System.out.println("found restaurant");
            return ResponseEntity.ok(restaurant.getRestaurantId());
        } else {
            System.out.println("no restaurant found");
            return ResponseEntity.notFound().build();
        }
    }

}

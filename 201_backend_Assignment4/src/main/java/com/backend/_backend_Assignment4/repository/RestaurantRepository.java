package com.backend._backend_Assignment4.repository;

import com.backend._backend_Assignment4.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {


    Restaurant findByName(String name);
}

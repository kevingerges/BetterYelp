package com.backend._backend_Assignment4.repository;

import com.backend._backend_Assignment4.model.Favorite;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    Set<Favorite> findByUserId(Long userId); // Assumes the User entity has an 'id' field, not 'userId'

    List<Favorite> findByUserId(Long userId, Sort sort);

    Optional<Favorite> findByUserIdAndRestaurantNameAndRestaurantPhone(Long userId, String restaurantName, String restaurantPhone);




}

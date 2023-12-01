package com.backend._backend_Assignment4.service;

import com.backend._backend_Assignment4.dto.FavoriteDTO;
import com.backend._backend_Assignment4.dto.ReservationDTO;
import com.backend._backend_Assignment4.model.Favorite;
import com.backend._backend_Assignment4.model.Reservation;
import com.backend._backend_Assignment4.model.User;
import com.backend._backend_Assignment4.repository.FavoriteRepository;
import com.backend._backend_Assignment4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FavoriteService {
    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;
    public List<FavoriteDTO> getUserFavorites(Long userId) {
        Set<Favorite> favoritesSet = favoriteRepository.findByUserId(userId);
        List<Favorite> favorites = new ArrayList<>(favoritesSet);
        return favorites.stream().map(favorite -> {
            FavoriteDTO dto = new FavoriteDTO();
            dto.setId(favorite.getId());
            dto.setUserId(favorite.getUser().getId());
            dto.setYelpRestaurantId(favorite.getYelpRestaurantId());
            dto.setRestaurantName(favorite.getRestaurantName());
            dto.setRestaurantPhone(favorite.getRestaurantPhone());
            dto.setRestaurantUrl(favorite.getRestaurantUrl());
            dto.setRestaurantImageUrl(favorite.getRestaurantImageUrl());

            // Set missing properties
            dto.setRestaurantAddress(favorite.getRestaurantAddress());
            dto.setCuisine(favorite.getCuisine());
            dto.setPrice(favorite.getPrice());
            dto.setRating(String.valueOf(favorite.getRating()));

            System.out.println("Mapped Image URL: " + dto.getRestaurantImageUrl()); // Debug log
            return dto;
        }).collect(Collectors.toList());
    }



    public void removeFavoriteByDetails(FavoriteDTO favoriteDTO) {
        System.out.println("Attempting to remove favorite for user: " + favoriteDTO.getUserId());
        Set<Favorite> favorites = favoriteRepository.findByUserId(favoriteDTO.getUserId());
        for (Favorite favorite : favorites) {
            if (favorite.getRestaurantName().equals(favoriteDTO.getRestaurantName())) {
                favoriteRepository.deleteById(favorite.getId());
                return;
            }
        }
        throw new RuntimeException("Favorite not found");
    }
    public void addFavorite(FavoriteDTO favoriteDTO) {
        if (favoriteDTO.getUserId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        User user = userRepository.findById(favoriteDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + favoriteDTO.getUserId()));

        // Check if the favorite already exists
        Optional<Favorite> existingFavorite = favoriteRepository.findByUserIdAndRestaurantNameAndRestaurantPhone(
                favoriteDTO.getUserId(),
                favoriteDTO.getRestaurantName(),
                favoriteDTO.getRestaurantPhone());

        if (existingFavorite.isPresent()) {
            // Handle the case where the favorite already exists
            throw new RuntimeException("Favorite already exists");
        }

        // Convert DTO to entity
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setYelpRestaurantId(favoriteDTO.getYelpRestaurantId());
        favorite.setRestaurantName(favoriteDTO.getRestaurantName());
        favorite.setRestaurantPhone(favoriteDTO.getRestaurantPhone());
        favorite.setRestaurantUrl(favoriteDTO.getRestaurantUrl());
        favorite.setRestaurantImageUrl(favoriteDTO.getRestaurantImageUrl());

        // Set missing properties
        favorite.setRestaurantAddress(favoriteDTO.getRestaurantAddress());
        favorite.setCuisine(favoriteDTO.getCuisine());
        favorite.setPrice(favoriteDTO.getPrice());
        favorite.setRating(favoriteDTO.getRating());

        favoriteRepository.save(favorite);

        // Log the result
        System.out.println("Favorite added: " + favorite);
    }

    private FavoriteDTO convertToDTO(Favorite favorite) {
        FavoriteDTO dto = new FavoriteDTO();
        dto.setId(favorite.getId());
        dto.setUserId(favorite.getUser().getId());
        dto.setYelpRestaurantId(favorite.getYelpRestaurantId());
        dto.setRestaurantName(favorite.getRestaurantName());
        dto.setRestaurantPhone(favorite.getRestaurantPhone());
        dto.setRestaurantUrl(favorite.getRestaurantUrl());
        dto.setRestaurantImageUrl(favorite.getRestaurantImageUrl());
        dto.setRestaurantAddress(favorite.getRestaurantAddress());
        dto.setCuisine(favorite.getCuisine());
        dto.setPrice(favorite.getPrice());
        dto.setRating(favorite.getRating().toString()); // Assuming rating is a Double
        // Add other necessary conversions if any

        return dto;
    }



    public List<FavoriteDTO> getFavoritesSortedByNameAsc(Long userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId, Sort.by(Sort.Direction.ASC, "restaurantName"));
        return favorites.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<FavoriteDTO> getFavoritesSortedByNameDesc(Long userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId, Sort.by(Sort.Direction.DESC, "restaurantName"));
        return favorites.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public List<FavoriteDTO> getFavoritesSortedByPriceAsc(Long userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId, Sort.by(Sort.Direction.ASC, "price"));
        return favorites.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public List<FavoriteDTO> getFavoritesSortedByPriceDesc(Long userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId, Sort.by(Sort.Direction.DESC, "price"));
        return favorites.stream().map(this::convertToDTO).collect(Collectors.toList());
    }


    public List<FavoriteDTO> getFavoritesSortedByMostRecent(Long userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId, Sort.by(Sort.Direction.DESC, "id"));
        return favorites.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    public List<FavoriteDTO> getFavoritesSortedByLeastRecent(Long userId) {
        List<Favorite> favorites = favoriteRepository.findByUserId(userId, Sort.by(Sort.Direction.ASC, "id"));
        return favorites.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

}

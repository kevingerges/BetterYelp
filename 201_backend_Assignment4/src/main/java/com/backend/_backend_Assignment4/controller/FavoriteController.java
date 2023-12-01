package com.backend._backend_Assignment4.controller;

import com.backend._backend_Assignment4.dto.FavoriteDTO;
import com.backend._backend_Assignment4.dto.ReservationDTO;
import com.backend._backend_Assignment4.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "http://localhost:5500")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/add")
    public ResponseEntity<?> addFavorite(@RequestBody FavoriteDTO favoriteDTO) {
        favoriteService.addFavorite(favoriteDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove")
    public ResponseEntity<?> removeFavorite(@RequestBody FavoriteDTO favoriteDTO) {
        favoriteService.removeFavoriteByDetails(favoriteDTO);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FavoriteDTO>> getUserFavorites(@PathVariable Long userId) {
        List<FavoriteDTO> favorites = favoriteService.getUserFavorites(userId);
        return ResponseEntity.ok(favorites);
    }


    @GetMapping("/user/{userId}/sort/name-asc")
    public ResponseEntity<List<FavoriteDTO>> getFavoritesSortedByNameAsc(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteService.getFavoritesSortedByNameAsc(userId));
    }

    @GetMapping("/user/{userId}/sort/name-desc")
    public ResponseEntity<List<FavoriteDTO>> getFavoritesSortedByNameDesc(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteService.getFavoritesSortedByNameDesc(userId));
    }

    @GetMapping("/user/{userId}/sort/price-asc")
    public ResponseEntity<List<FavoriteDTO>> getFavoritesSortedByPriceAsc(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteService.getFavoritesSortedByPriceAsc(userId));
    }

    @GetMapping("/user/{userId}/sort/price-desc")
    public ResponseEntity<List<FavoriteDTO>> getFavoritesSortedByPriceDesc(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteService.getFavoritesSortedByPriceDesc(userId));
    }

    @GetMapping("/user/{userId}/sort/most-recent")
    public ResponseEntity<List<FavoriteDTO>> getFavoritesSortedByMostRecent(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteService.getFavoritesSortedByMostRecent(userId));
    }

    @GetMapping("/user/{userId}/sort/least-recent")
    public ResponseEntity<List<FavoriteDTO>> getFavoritesSortedByLeastRecent(@PathVariable Long userId) {
        return ResponseEntity.ok(favoriteService.getFavoritesSortedByLeastRecent(userId));
    }
}
package com.backend._backend_Assignment4.controller;


import com.backend._backend_Assignment4.dto.ReservationDTO;
import com.backend._backend_Assignment4.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/reservations")
//@RequestMapping("/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//@CrossOrigin(origins = "http://127.0.0.1:5500/")
public class ReservationController {
    private final Logger logger = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private ReservationService reservationService;

    @PostMapping(value = "/create", consumes = "application/json")
    public ResponseEntity<ReservationDTO> addReservation(@RequestBody ReservationDTO reservationDTO) {
        try {
            logger.info("Reservation created successfully.");
            ReservationDTO createdReservation = reservationService.addReservation(reservationDTO);
            return ResponseEntity.ok(createdReservation);
        }catch (Exception e) {
            logger.error("Error creating reservation.", e);
        }
        return null;
    }

    @DeleteMapping("/delete/{reservationId}")
    public ResponseEntity<?> removeReservation(@PathVariable Long reservationId) {
        reservationService.removeReservationById(reservationId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationDTO>> getUserReservations(
            @PathVariable Long userId,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String sortOrder
    ) {
        List<ReservationDTO> reservations;

        if (sortBy != null && sortOrder != null) {
            if (sortBy.equals("mostRecent") && sortOrder.equals("desc")) {
                // Sort by reservationDate in descending order (most recent first)
                reservations = reservationService.getUserReservationsSortedByMostRecent(userId);
            } else if (sortBy.equals("leastRecent") && sortOrder.equals("asc")) {
                // Sort by reservationDate in ascending order (least recent first)
                reservations = reservationService.getUserReservationsSortedByLeastRecent(userId);
            } else {
                // Handle invalid sortBy or sortOrder values
                return ResponseEntity.badRequest().build();
            }
        } else {
            // If no sorting parameters are provided, fetch reservations without sorting
            reservations = reservationService.getUserReservations(userId);
        }

        return ResponseEntity.ok(reservations);
    }




}

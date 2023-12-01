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
    public ResponseEntity<List<ReservationDTO>> getUserReservations(@PathVariable Long userId) {
        List<ReservationDTO> reservations = reservationService.getUserReservations(userId);
        return ResponseEntity.ok(reservations);
    }


    @GetMapping("/most-recent")
    public ResponseEntity<List<ReservationDTO>> getMostRecentReservations() {
        List<ReservationDTO> reservations = reservationService.getMostRecentReservations();
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/least-recent")
    public ResponseEntity<List<ReservationDTO>> getLeastRecentReservations() {
        List<ReservationDTO> reservations = reservationService.getLeastRecentReservations();
        return ResponseEntity.ok(reservations);
    }



}

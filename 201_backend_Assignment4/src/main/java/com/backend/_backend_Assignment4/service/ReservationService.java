package com.backend._backend_Assignment4.service;
import org.springframework.data.domain.Sort;

import com.backend._backend_Assignment4.dto.ReservationDTO;
import com.backend._backend_Assignment4.model.Reservation;
import com.backend._backend_Assignment4.model.Restaurant;
import com.backend._backend_Assignment4.model.User;
import com.backend._backend_Assignment4.repository.ReservationRepository;
import com.backend._backend_Assignment4.repository.RestaurantRepository;
import com.backend._backend_Assignment4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {


    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public Optional<Restaurant> findRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }


    public ReservationDTO addReservation(ReservationDTO reservationDTO) {
        // Fetch the user or throw an exception if not found
        User user = userRepository.findById(reservationDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch the restaurant or throw an exception if not found
        Restaurant restaurant = restaurantRepository.findById(reservationDTO.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        // Create a new Reservation instance and set properties from the reservationDTO and fetched entities
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setRestaurant(restaurant); // This associates the Restaurant entity with the Reservation
        reservation.setReservationTime(reservationDTO.getReservationTime());
        reservation.setReservationDate(reservationDTO.getReservationDate());
        reservation.setDetailRequested(reservationDTO.getDetailRequested());

        // Set additional details if necessary
        reservation.setRestaurantName(restaurant.getName());
        reservation.setRestaurantAddress(restaurant.getAddress());
        reservation.setRestaurantImageUrl(restaurant.getImageUrl());

        // Save the reservation entity to the database
        Reservation savedReservation = reservationRepository.save(reservation);

        // Return a DTO representation of the saved reservation
        return mapToReservationDTO(savedReservation);
    }


        public void removeReservationById (Long reservationId){
            reservationRepository.deleteById(reservationId);
        }

        public List<ReservationDTO> getUserReservations (Long userId){
            List<Reservation> reservations = reservationRepository.findAllByUserId(userId);
            return reservations.stream()
                    .map(this::mapToReservationDTO)
                    .collect(Collectors.toList());
        }


        private ReservationDTO mapToReservationDTO (Reservation reservation){
            ReservationDTO dto = new ReservationDTO();
            dto.setReservationId(reservation.getReservationId());
            dto.setUserId(reservation.getUser().getId());
            dto.setReservationId(reservation.getReservationId());
            dto.setUserId(reservation.getUser().getId());
            dto.setRestaurantName(reservation.getRestaurantName());

            dto.setRestaurantAddress(reservation.getRestaurantAddress());
            dto.setRestaurantImageUrl(reservation.getRestaurantImageUrl());
            dto.setReservationDate(reservation.getReservationDate());
            dto.setReservationTime(reservation.getReservationTime());
            dto.setDetailRequested(reservation.getDetailRequested());

            dto.setReservationId(reservation.getReservationId());
            dto.setUserId(reservation.getUser().getId());
            dto.setRestaurantId(reservation.getRestaurant().getRestaurantId());
            dto.setRestaurantName(reservation.getRestaurant().getName());
            dto.setRestaurantAddress(reservation.getRestaurant().getAddress());
            dto.setRestaurantImageUrl(reservation.getRestaurant().getImageUrl());
            return dto;
        }
    public List<ReservationDTO> getUserReservationsSortedByMostRecent(Long userId) {
        Sort sort = Sort.by(Sort.Order.desc("reservationDate"), Sort.Order.desc("reservationTime"));
        List<Reservation> reservations = reservationRepository.findByUserIdOrderByReservationDateDescReservationTimeDesc(userId);
        return convertToDTOs(reservations);
    }

    public List<ReservationDTO> getUserReservationsSortedByLeastRecent(Long userId) {
        Sort sort = Sort.by(Sort.Order.asc("reservationDate"), Sort.Order.asc("reservationTime"));
        List<Reservation> reservations = reservationRepository.findByUserIdOrderByReservationDateAscReservationTimeAsc(userId);
        return convertToDTOs(reservations);
    }

    private List<ReservationDTO> convertToDTOs(List<Reservation> reservations) {
            List<ReservationDTO> reservationDTOs = new ArrayList<>();

            for (Reservation reservation : reservations) {
                ReservationDTO reservationDTO = new ReservationDTO();
                reservationDTO.setReservationId(reservation.getReservationId());
//                reservationDTO.setUserId(reservation.getUser());
//                reservationDTO.setRestaurantId(reservation.getRestaurant());
                reservationDTO.setRestaurantName(reservation.getRestaurantName());
                reservationDTO.setRestaurantAddress(reservation.getRestaurantAddress());
                reservationDTO.setRestaurantImageUrl(reservation.getRestaurantImageUrl());
                reservationDTO.setReservationDate(reservation.getReservationDate());
                reservationDTO.setReservationTime(reservation.getReservationTime());
                reservationDTO.setDetailRequested(reservation.getDetailRequested());

                reservationDTOs.add(reservationDTO);
            }

            return reservationDTOs;
    }
}

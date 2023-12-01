package com.backend._backend_Assignment4.repository;

import com.backend._backend_Assignment4.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long > {


        List<Reservation> findAllByUserId(Long userId);

        List<Reservation> findByUserIdOrderByReservationDateDescReservationTimeDesc(Long userId);

        List<Reservation> findByUserIdOrderByReservationDateAscReservationTimeAsc(Long userId);


}

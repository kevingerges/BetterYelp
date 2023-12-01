package com.backend._backend_Assignment4.dto;

import java.time.LocalDate;

public class ReservationDTO {
    private Long reservationId;
    private Long userId;
    private Long restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private String restaurantImageUrl;
    private String reservationDate;
    private String reservationTime;
    private String detailRequested;


    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantImageUrl() {
        return restaurantImageUrl;
    }

    public void setRestaurantImageUrl(String restaurantImageUrl) {
        this.restaurantImageUrl = restaurantImageUrl;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    // Getter for reservationTime
    public String getReservationTime() {
        return this.reservationTime;
    }
    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
    }

    public String getDetailRequested() {
        return detailRequested;
    }

    public void setDetailRequested(String detailRequested) {
        this.detailRequested = detailRequested;
    }


    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getName() {
        return restaurantName;
    }


}
package com.backend._backend_Assignment4.model;

import jakarta.persistence.*;

@Entity
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id") // Make sure this matches the column name in the database
    private User user;
    @Column(name = "yelp_restaurant_id")
    private String yelpRestaurantId; // Unique identifier from Yelp

    @Column(name = "restaurant_name")
    private String restaurantName;

    @Column(name = "restaurant_phone")
    private String restaurantPhone;

    @Column(name = "restaurant_url")
    private String restaurantUrl;

    @Column(name = "restaurant_image_url") // Make sure this matches your database column name
    private String restaurantImageUrl;

    @Column(name = "restaurant_address") // Add the missing property
    private String restaurantAddress;

    @Column(name = "cuisine") // Add the missing property
    private String cuisine;

    @Column(name = "price") // Add the missing property
    private String price;

    @Column(name = "rating") // Add the missing property
    private Double rating;

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getPrice() {
        return price;
    }

    public Double getRating() {
        return rating;
    }

    // Constructors
    public Favorite() {
    }

    public Favorite(User user, String yelpRestaurantId, String restaurantName, String restaurantPhone, String restaurantUrl, String restaurantImageUrl) {
        this.user = user;
        this.yelpRestaurantId = yelpRestaurantId;
        this.restaurantName = restaurantName;
        this.restaurantPhone = restaurantPhone;
        this.restaurantUrl = restaurantUrl;
        this.restaurantImageUrl = restaurantImageUrl;
    }

    public String getRestaurantImageUrl() {
        return restaurantImageUrl;
    }

    public void setRestaurantImageUrl(String restaurantImageUrl) {
        this.restaurantImageUrl = restaurantImageUrl;
    }
    // Getters and Setters

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getYelpRestaurantId() {
        return yelpRestaurantId;
    }

    public void setYelpRestaurantId(String yelpRestaurantId) {
        this.yelpRestaurantId = yelpRestaurantId;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantPhone() {
        return restaurantPhone;
    }

    public void setRestaurantPhone(String restaurantPhone) {
        this.restaurantPhone = restaurantPhone;
    }

    public String getRestaurantUrl() {
        return restaurantUrl;
    }

    public void setRestaurantUrl(String restaurantUrl) {
        this.restaurantUrl = restaurantUrl;
    }

    public String getRestaurantName() {
        return restaurantName;

    }

}
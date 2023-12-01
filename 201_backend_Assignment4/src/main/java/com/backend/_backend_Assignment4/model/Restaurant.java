package com.backend._backend_Assignment4.model;


import jakarta.persistence.*;

@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long restaurantId;
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    private String name;
    private Double latitude;
    private Double longitude;
    private String address;
    private String phone;
    private String price;
    private String cuisine;
    private Double rating;
    private Integer reviewCount;
    private String url;
    private String imageUrl;

    // and getters and setters
    public Restaurant(String name, Double latitude, Double longitude, String address,
                      Double rating, Integer reviewCount, String imageUrl,
                      String url, String phone, String cuisine, String price) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.phone = phone;
        this.price = price;
        this.cuisine = cuisine;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public Restaurant() {

    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }


    public Double getLatitude() {
        return latitude;
    }


    public Double getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public Double getRating() {
        return rating;
    }


    public Integer getReviewCount() {
        return reviewCount;
    }


    public String getUrl() {
        return url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPhone() {
        return phone;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId=" + restaurantId +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", price='" + price + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", rating=" + rating +
                ", reviewCount=" + reviewCount +
                ", url='" + url + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }


}

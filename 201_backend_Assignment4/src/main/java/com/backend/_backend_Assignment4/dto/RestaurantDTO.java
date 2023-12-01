package com.backend._backend_Assignment4.dto;


public class RestaurantDTO {
    private String name;
    private double latitude;
    private double longitude;
    private String address; // Optional, if you want to include the address
    private double rating; // Optional, for the restaurant's rating
    private int reviewCount; // Optional, for the number of reviews
    private String imageUrl; // Optional, for the URL of the restaurant's image
    private String yelpUrl; // Optional, for the URL of the restaurant's image
    private String url;
    private String phone;
    private String cuisine;
    private String price;

    public String getYelpUrl() {
        return yelpUrl;
    }

    public void setYelpUrl(String yelpUrl) {
        this.yelpUrl = yelpUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

// Constructor


    public RestaurantDTO(String name, double latitude, double longitude, String address, double rating, int reviewCount, String imageUrl, String url, String phone, String cuisine, String price) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.rating = rating;
        this.reviewCount = reviewCount;
        this.imageUrl = imageUrl;
        this.url = url;
        this.phone = phone;
        this.cuisine = cuisine;
        this.price = price;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public RestaurantDTO() {
    }

    @Override
    public String toString() {
        return "RestaurantDTO{" +
                "name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", address='" + address + '\'' +
                ", rating=" + rating +
                ", reviewCount=" + reviewCount +
                ", imageUrl='" + imageUrl + '\'' +
                ", yelpUrl='" + yelpUrl + '\'' +
                '}';
    }
}

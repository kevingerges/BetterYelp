package com.backend._backend_Assignment4.util;

import com.backend._backend_Assignment4.model.Restaurant;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


import java.util.ArrayList;

@Service
public class YelpAPIUtil {

    private final String apiKey = "hahanottoday";
    public List<Restaurant> searchRestaurants(String query, Double latitude, Double longitude, String sort) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String formattedQuery = formatQueryParameter(query);
        String url = buildSearchUrl(latitude, longitude, formattedQuery, sort);
        Request request = buildRequest(url);

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                return parseResponse(responseBody);
            } else {
                throw new IOException("Failed Yelp API call: " + response);
            }
        }
    }

    private String buildSearchUrl(Double latitude, Double longitude, String query, String sort) {
        return "https://api.yelp.com/v3/businesses/search?latitude=" + latitude
                + "&longitude=" + longitude + "&term=" + query + "&sort_by=" + sort + "&limit=20";
    }

    private Request buildRequest(String url) {
        return new Request.Builder()
                .url(url)
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();
    }
    private List<Restaurant> parseResponse(String responseBody) throws IOException {
        JSONParser parser = new JSONParser();
        List<Restaurant> restaurants = new ArrayList<>();
        try {
            JSONObject jsonResponse = (JSONObject) parser.parse(responseBody);
            JSONArray businesses = (JSONArray) jsonResponse.get("businesses");
            System.out.println("Number of businesses found: " + businesses.size());
            for (Object obj : businesses) {
                JSONObject business = (JSONObject) obj;
                String name = (String) business.get("name");
                String imageUrl = (String) business.get("image_url");
                String yelpUrl = (String) business.get("url");
                Number rating = (Number) business.get("rating");
                Number reviewCount = (Number) business.get("review_count");
                String phone = (String) business.get("phone"); // Phone number
                // Extracting categories to get cuisine type
                JSONArray categories = (JSONArray) business.get("categories");
                String cuisine = categories.size() > 0 ? ((JSONObject)categories.get(0)).get("title").toString() : "Not Available";
                String price = (String) business.get("price"); // Price level
                JSONObject location = (JSONObject) business.get("location");
                String address = String.join(", ", (JSONArray)location.get("display_address"));
                JSONObject coordinates = (JSONObject) business.get("coordinates");
                Double latitude = coordinates.get("latitude") != null ? (Double) coordinates.get("latitude") : null;
                Double longitude = coordinates.get("longitude") != null ? (Double) coordinates.get("longitude") : null;

                if (latitude != null && longitude != null) {
                    // Assuming your Restaurant constructor can handle all these parameters.
                    Restaurant restaurant = new Restaurant(name, latitude, longitude, address,
                            rating.doubleValue(), reviewCount.intValue(),
                            imageUrl, yelpUrl, phone, cuisine, price);
                    restaurants.add(restaurant);
                    System.out.println("Added restaurant: " + name);
                } else {
                    System.out.println("Missing coordinates for: " + name);
                }
            }
        } catch (ParseException e) {
            System.err.println("Error parsing JSON response: " + e.getMessage());
            throw new IOException("Error parsing JSON response", e);
        }
        System.out.println("Total Restaurants added: " + restaurants.size());
        return restaurants;
    }



    private String formatQueryParameter(String query) {
        return query.replace(" ", "%20").replace("'", "%27");
    }
}


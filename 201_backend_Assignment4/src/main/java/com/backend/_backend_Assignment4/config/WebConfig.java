package com.backend._backend_Assignment4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // This allows all paths
                .allowedOrigins("http://127.0.0.1:5500") // Add your frontend origin here
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Or whatever methods you need
                .allowedHeaders("*"); // Allows all headers
    }
}

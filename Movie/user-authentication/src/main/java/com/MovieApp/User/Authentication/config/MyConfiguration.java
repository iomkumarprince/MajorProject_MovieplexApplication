package com.MovieApp.User.Authentication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Replace with the appropriate origin
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Add the allowed HTTP methods
                .allowedHeaders("Origin", "Content-Type", "Accept", "Authorization") // Add the allowed headers
                .maxAge(3600); // Set the max age of the CORS cache
    }
}

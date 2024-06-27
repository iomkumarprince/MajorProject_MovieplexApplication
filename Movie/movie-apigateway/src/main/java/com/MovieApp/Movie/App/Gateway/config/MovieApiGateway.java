package com.MovieApp.Movie.App.Gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


@Configuration
public class MovieApiGateway {
    @Bean
    public RouteLocator routeUrl(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes().route(p -> p.path("/api/v1/**")
                        .uri("lb://UserAuth"))
                .route(p -> p.path("/movie/api/v1/**").
                        uri("lb://MovieService")).
                route(p->p.path("/api/contactEmail/**").uri("lb://ContactEmailService"))
                .route(p -> p.path("/api/v2/sendEmail/**").uri("lb://EmailService")).build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}

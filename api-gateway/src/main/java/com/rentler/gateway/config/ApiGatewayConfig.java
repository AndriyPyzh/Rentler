package com.rentler.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Valid;

@Configuration
public class ApiGatewayConfig {

    @Value("${apartment.service.url}")
    private String apartmentServiceUrl;

    @Value("${account.service.url}")
    private String accountServiceUrl;

    @Value("${auth.service.url}")
    private String authServiceUrl;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/apartments/**")
                        .uri(apartmentServiceUrl + "/apartments"))
                .route(r -> r.path("/accounts/**")
                        .uri(accountServiceUrl + "/accounts"))
                .route(r -> r.path("/auth/**")
                        .uri(authServiceUrl + "/auth"))
                .build();

    }
}


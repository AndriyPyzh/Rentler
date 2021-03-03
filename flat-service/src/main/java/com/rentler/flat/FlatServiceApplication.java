package com.rentler.flat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FlatServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlatServiceApplication.class, args);
    }

}

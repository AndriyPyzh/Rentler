package com.rentler.apartment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "notification-service")
public interface NotificationServiceClient {

    @PostMapping(value = "/notifications/new-application", consumes = MediaType.APPLICATION_JSON_VALUE)
    void sendNewApplication(@RequestParam String username);

    @PostMapping(value = "/notifications/application-approved", consumes = MediaType.APPLICATION_JSON_VALUE)
    void sendApplicationApproved(@RequestParam String username);

    @PostMapping(value = "/notifications/application-rejected", consumes = MediaType.APPLICATION_JSON_VALUE)
    void sendApplicationRejected(@RequestParam String username);
}
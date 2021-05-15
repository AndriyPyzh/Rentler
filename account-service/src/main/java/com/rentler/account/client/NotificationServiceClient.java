package com.rentler.account.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "notification-service")
public interface NotificationServiceClient {

    @PostMapping(value = "/notifications/welcome", consumes = MediaType.APPLICATION_JSON_VALUE)
    void sendWelcomeMail(@RequestParam String email);

}
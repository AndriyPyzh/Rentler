package com.rentler.apartment.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service", url = "${ACCOUNT_SERVICE_URL:http://localhost:8100}")
public interface AccountServiceClient {

    @GetMapping(value = "/accounts/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void getAccount(@PathVariable Long id);
}
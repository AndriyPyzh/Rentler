package com.rentler.notification.client;

import com.rentler.notification.dto.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service", url = "${ACCOUNT_SERVICE_URL:http://localhost:8100}")
public interface AccountServiceClient {

    @GetMapping(value = "/accounts/{username}", consumes = MediaType.APPLICATION_JSON_VALUE)
    AccountDto getAccount(@PathVariable String username);
}
package com.rentler.account.client;

import com.rentler.account.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "auth-service")
public interface AuthServiceClient {

    @RequestMapping(method = RequestMethod.POST, value = "/auth/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    void createUser(UserDto user);

}
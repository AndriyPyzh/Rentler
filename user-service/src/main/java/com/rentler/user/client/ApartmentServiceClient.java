package com.rentler.user.client;

import com.rentler.user.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "apartment-service", fallback = ApartmentServiceClientFallback.class)
public interface ApartmentServiceClient {

    @RequestMapping(method = RequestMethod.POST, value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    void createUser(UserDto userDto);
}

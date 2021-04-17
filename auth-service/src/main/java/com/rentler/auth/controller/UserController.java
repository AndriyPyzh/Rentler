package com.rentler.auth.controller;

import com.rentler.auth.dto.UserDto;
import com.rentler.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/current")
    public Principal getCurrentUser(Principal principal) {
        return principal;
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody UserDto dto) {
        userService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}

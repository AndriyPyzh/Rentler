package com.rentler.auth.controller;

import com.rentler.auth.dto.SuccessLoginDto;
import com.rentler.auth.dto.UserDto;
import com.rentler.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    private AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessLoginDto> login(@Valid @RequestBody UserDto dto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(authService.login(dto));
    }
}

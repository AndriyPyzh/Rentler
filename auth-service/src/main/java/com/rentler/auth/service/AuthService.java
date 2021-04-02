package com.rentler.auth.service;

import com.rentler.auth.dto.SuccessLoginDto;
import com.rentler.auth.dto.UserDto;
import com.rentler.auth.entity.User;
import com.rentler.auth.enums.Role;
import com.rentler.auth.exception.exceptions.BadUsernameOrPasswordException;
import com.rentler.auth.repository.UserRepository;
import com.rentler.auth.util.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final Jwt jwt;

    @Autowired
    public AuthService(UserRepository repository,
                       Jwt jwt) {
        this.repository = repository;
        this.jwt = jwt;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public SuccessLoginDto login(UserDto userDto) {

        User user = repository.findByUsername(userDto.getUsername())
                .filter(usr -> passwordEncoder.matches(userDto.getPassword(), usr.getPassword()))
                .orElseThrow(() -> new BadUsernameOrPasswordException("Wrong username or password"));

        String token = jwt.createAccessToken(userDto.getUsername(), Role.ROLE_USER);

        return new SuccessLoginDto(user.getUsername(), token);
    }
}


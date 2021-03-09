package com.rentler.apartment.service;

import com.rentler.apartment.dto.UserDto;
import com.rentler.apartment.entity.User;
import com.rentler.apartment.exception.exceptions.UserNotFoundException;
import com.rentler.apartment.mapper.UserMapper;
import com.rentler.apartment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto create(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with such username not found"));
    }
}


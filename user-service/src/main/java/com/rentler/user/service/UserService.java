package com.rentler.user.service;

import com.rentler.user.client.ApartmentServiceClient;
import com.rentler.user.dto.UserDto;
import com.rentler.user.entity.User;
import com.rentler.user.exception.exceptions.UserNotFoundException;
import com.rentler.user.mapper.UserMapper;
import com.rentler.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ApartmentServiceClient apartmentServiceClient;

    @Autowired
    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       ApartmentServiceClient apartmentServiceClient) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.apartmentServiceClient = apartmentServiceClient;
    }

    public UserDto create(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        apartmentServiceClient.createUser(userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    public UserDto getByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with such username not found"));
        return userMapper.toDto(user);
    }

    public UserDto getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with such id not found"));
        return userMapper.toDto(user);
    }

    public List<UserDto> getAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }
}


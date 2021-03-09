package com.rentler.user.bootstrap;

import com.rentler.user.entity.User;
import com.rentler.user.mapper.UserMapper;
import com.rentler.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserLoader implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserLoader(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        User user = User.builder()
                .username("andriy")
                .email("andriy@gmail.com")
                .password("1111")
                .build();

        userRepository.save(user);
    }
}


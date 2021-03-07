package com.rentler.apartment.bootstrap;

import com.rentler.apartment.entity.Address;
import com.rentler.apartment.entity.Apartment;
import com.rentler.apartment.entity.User;
import com.rentler.apartment.mapper.ApartmentMapper;
import com.rentler.apartment.repository.ApartmentRepository;
import com.rentler.apartment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ApartmentLoader implements ApplicationListener<ApplicationReadyEvent> {
    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;
    private final ApartmentMapper apartmentMapper;

    @Autowired
    public ApartmentLoader(ApartmentRepository apartmentRepository, UserRepository userRepository, ApartmentMapper apartmentMapper) {
        this.apartmentRepository = apartmentRepository;
        this.userRepository = userRepository;
        this.apartmentMapper = apartmentMapper;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        User user = User.builder()
                .username("andriy")
                .build();


        Address address = Address.builder()
                .city("lviv")
                .street("shevchenka")
                .houseNumber(80)
                .build();


        Apartment apartment = Apartment.builder()
                .title("my flat")
                .description("some desc..")
                .price(10000)
                .rooms(3)
                .floor(5)
                .address(address)
                .user(user)
                .build();

        userRepository.save(user);

        apartmentRepository.save(apartment);

    }
}


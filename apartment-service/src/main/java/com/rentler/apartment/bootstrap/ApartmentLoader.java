package com.rentler.apartment.bootstrap;

import com.rentler.apartment.entity.Address;
import com.rentler.apartment.entity.Apartment;
import com.rentler.apartment.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ApartmentLoader implements ApplicationListener<ApplicationReadyEvent> {
    private final ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentLoader(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
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
                .userId(1L)
                .build();

        apartmentRepository.save(apartment);

    }
}


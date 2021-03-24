package com.rentler.apartment.mapper;

import com.rentler.apartment.dto.ApartmentDto;
import com.rentler.apartment.entity.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApartmentMapper extends Mapper<Apartment, ApartmentDto> {

    @Autowired
    public ApartmentMapper() {
        super(Apartment.class, ApartmentDto.class);
    }
}


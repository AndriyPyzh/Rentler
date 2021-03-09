package com.rentler.apartment.mapper;

import com.rentler.apartment.dto.ApartmentDto;
import com.rentler.apartment.entity.Apartment;
import com.rentler.apartment.entity.User;
import com.rentler.apartment.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ApartmentMapper implements Mapper<Apartment, ApartmentDto> {

    private final ModelMapper modelMapper;
    private final UserService userService;

    @Autowired
    public ApartmentMapper(UserService userService,
                           ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Apartment.class, ApartmentDto.class)
                .addMappings(m -> m.skip(ApartmentDto::setUser));
        modelMapper.createTypeMap(ApartmentDto.class, Apartment.class)
                .addMappings(m -> m.skip(Apartment::setUser));
    }

    @Override
    public Apartment toEntity(ApartmentDto dto) {
        User user = userService.getByUsername(dto.getUser());
        Apartment apartment = modelMapper.map(dto, Apartment.class);
        apartment.setUser(user);
        return apartment;
    }

    @Override
    public ApartmentDto toDto(Apartment entity) {
        ApartmentDto apartmentDto = modelMapper.map(entity, ApartmentDto.class);
        apartmentDto.setUser(entity.getUser().getUsername());
        return apartmentDto;
    }
}


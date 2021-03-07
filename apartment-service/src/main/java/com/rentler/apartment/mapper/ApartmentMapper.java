package com.rentler.apartment.mapper;

import com.rentler.apartment.dto.ApartmentDto;
import com.rentler.apartment.entity.Apartment;
import com.rentler.apartment.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ApartmentMapper extends Mapper<Apartment, ApartmentDto> {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public ApartmentMapper(UserRepository userRepository,
                           ModelMapper modelMapper) {
        super(Apartment.class, ApartmentDto.class);
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void setupMapper() {
        modelMapper.createTypeMap(Apartment.class, ApartmentDto.class)
                .addMappings(m -> m.skip(ApartmentDto::setUser)).setPostConverter(toDtoConverter());
        modelMapper.createTypeMap(ApartmentDto.class, Apartment.class)
                .addMappings(m -> m.skip(Apartment::setUser)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapFieldsFromDto(ApartmentDto source, Apartment destination) {
        String username = source.getUser();
        destination.setUser(userRepository.findUserByUsername(username));
    }

    @Override
    public void mapFieldsFromEntity(Apartment source, ApartmentDto destination) {
        destination.setUser(source.getUser().getUsername());
    }

}


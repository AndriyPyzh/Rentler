package com.rentler.apartment.service;

import com.rentler.apartment.dto.ApartmentDto;
import com.rentler.apartment.entity.Apartment;
import com.rentler.apartment.exception.exceptions.ApartmentNotFoundException;
import com.rentler.apartment.mapper.ApartmentMapper;
import com.rentler.apartment.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final ApartmentMapper apartmentMapper;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository,
                            ApartmentMapper apartmentMapper) {
        this.apartmentRepository = apartmentRepository;
        this.apartmentMapper = apartmentMapper;
    }

    public ApartmentDto save(ApartmentDto apartmentDto) {
        Apartment apartment = apartmentMapper.toEntity(apartmentDto);
        return apartmentMapper.toDto(apartmentRepository.save(apartment));
    }

    public List<ApartmentDto> getAll() {
        return apartmentRepository.findAll()
                .stream()
                .map(apartmentMapper::toDto)
                .collect(Collectors.toList());
    }

    public ApartmentDto getById(Long id) {
        Apartment apartment = apartmentRepository.findById(id)
                .orElseThrow(() -> new ApartmentNotFoundException("Apartment with such id not found"));
        return apartmentMapper.toDto(apartment);
    }
}


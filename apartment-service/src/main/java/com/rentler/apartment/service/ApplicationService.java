package com.rentler.apartment.service;

import com.rentler.apartment.dto.ApplicationDto;
import com.rentler.apartment.entity.Apartment;
import com.rentler.apartment.entity.Application;
import com.rentler.apartment.exception.ApplicationNotFoundException;
import com.rentler.apartment.mapper.ApartmentMapper;
import com.rentler.apartment.mapper.ApplicationMapper;
import com.rentler.apartment.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;
    private final ApartmentService apartmentService;
    private final ApartmentMapper apartmentMapper;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository,
                              ApplicationMapper applicationMapper,
                              ApartmentService apartmentService,
                              ApartmentMapper apartmentMapper) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
        this.apartmentService = apartmentService;
        this.apartmentMapper = apartmentMapper;
    }


    public ApplicationDto create(ApplicationDto applicationDto, String username) {
        Application application = applicationMapper.toEntity(applicationDto);
        application.setOwner(username);
        return applicationMapper.toDto(applicationRepository.save(application));
    }


    public ApplicationDto update(ApplicationDto applicationDto) {
        Application application = applicationRepository.findById(applicationDto.getId())
                .orElseThrow(() -> new ApplicationNotFoundException("Application with such id not found: " + applicationDto.getId()));

        Apartment apartment = apartmentMapper.toEntity(apartmentService.getById(applicationDto.getApartmentId()));

        application.setApartment(apartment);
        application.setPrice(applicationDto.getPrice());

        return applicationMapper.toDto(applicationRepository.save(application));
    }

    public List<ApplicationDto> getAllByOwner(String username) {
        return applicationRepository.findAllByOwner(username)
                .stream()
                .map(applicationMapper::toDto)
                .collect(Collectors.toList());
    }
}

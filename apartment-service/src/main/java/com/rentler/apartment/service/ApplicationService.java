package com.rentler.apartment.service;

import com.rentler.apartment.client.NotificationServiceClient;
import com.rentler.apartment.dto.ApplicationDto;
import com.rentler.apartment.entity.Apartment;
import com.rentler.apartment.entity.Application;
import com.rentler.apartment.enums.ApplicationStatus;
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
    private final NotificationServiceClient notificationServiceClient;

    @Autowired
    public ApplicationService(ApplicationRepository applicationRepository,
                              ApplicationMapper applicationMapper,
                              ApartmentService apartmentService,
                              ApartmentMapper apartmentMapper,
                              NotificationServiceClient notificationServiceClient) {
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
        this.apartmentService = apartmentService;
        this.apartmentMapper = apartmentMapper;
        this.notificationServiceClient = notificationServiceClient;
    }


    public ApplicationDto create(ApplicationDto applicationDto, String username) {
        Application application = applicationMapper.toEntity(applicationDto);
        application.setStatus(ApplicationStatus.PENDING);
        application.setOwner(username);
        try {
            notificationServiceClient.sendNewApplication(application.getApartment().getOwner());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return applicationMapper.toDto(applicationRepository.save(application));
    }


    public ApplicationDto update(Long id, ApplicationDto applicationDto) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ApplicationNotFoundException("Application with such id not found: " + id));

        Apartment apartment = apartmentMapper.toEntity(apartmentService.getById(applicationDto.getApartmentId()));

        if (applicationDto.getStatus() != null) {

            if (applicationDto.getStatus().equals(ApplicationStatus.APPROVED)) {
                rejectAllByApartment(apartment);
                try {
                    notificationServiceClient.sendApplicationApproved(apartment.getOwner());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            application.setStatus(applicationDto.getStatus());
        }

        application.setPrice(applicationDto.getPrice());
        application.setApartment(apartment);

        return applicationMapper.toDto(applicationRepository.save(application));
    }

    public List<ApplicationDto> getAllByOwner(String username) {
        return applicationRepository.findAllByOwner(username)
                .stream()
                .map(applicationMapper::toDto)
                .collect(Collectors.toList());
    }


    private void rejectAllByApartment(Apartment apartment) {
        applicationRepository.findAllByApartment(apartment).forEach(a -> {
            a.setStatus(ApplicationStatus.REJECTED);
            applicationRepository.save(a);
            try {
                notificationServiceClient.sendApplicationRejected(apartment.getOwner());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void delete(Long id) {
        applicationRepository.deleteById(id);
    }
}

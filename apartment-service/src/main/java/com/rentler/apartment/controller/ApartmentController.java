package com.rentler.apartment.controller;

import com.rentler.apartment.dto.ApartmentDto;
import com.rentler.apartment.dto.ApartmentUpdateDto;
import com.rentler.apartment.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping
public class ApartmentController {
    private final ApartmentService apartmentService;

    @Autowired
    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping
    public ResponseEntity<List<ApartmentDto>> getApartments(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(apartmentService.getByPage(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApartmentDto> getApartment(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(apartmentService.getById(id));
    }

    @GetMapping("/amenities")
    public ResponseEntity<List<String>> getAmenities() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(apartmentService.getAmenities());
    }

    @GetMapping("/types")
    public ResponseEntity<List<String>> getApartmentTypes() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(apartmentService.getApartmentTypes());
    }

    @PostMapping
    public ResponseEntity<ApartmentDto> createApartment(@RequestBody @Valid ApartmentUpdateDto apartmentDto,
                                                        Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apartmentService.create(apartmentDto, principal.getName()));
    }

    @PutMapping
    public ResponseEntity<ApartmentDto> updateApartment(@RequestBody @Valid ApartmentUpdateDto apartmentUpdateDto,
                                                        Principal principal) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(apartmentService.update(apartmentUpdateDto, principal.getName()));
    }

}

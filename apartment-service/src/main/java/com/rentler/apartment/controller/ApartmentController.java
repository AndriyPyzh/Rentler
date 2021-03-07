package com.rentler.apartment.controller;

import com.rentler.apartment.dto.ApartmentDto;
import com.rentler.apartment.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/")
public class ApartmentController {
    private final ApartmentService apartmentService;

    @Autowired
    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping
    public ResponseEntity<List<ApartmentDto>> getApartments() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(apartmentService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ApartmentDto> getApartment(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(apartmentService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ApartmentDto> createApartment(@RequestBody @Valid ApartmentDto apartmentDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apartmentService.save(apartmentDto));
    }

}

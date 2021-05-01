package com.rentler.apartment.controller;

import com.rentler.apartment.dto.ApplicationDto;
import com.rentler.apartment.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping
    public ResponseEntity<ApplicationDto> createApplication(@RequestBody @Valid ApplicationDto applicationDto,
                                                            Principal principal) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(applicationService.create(applicationDto, principal.getName()));
    }

    @PutMapping
    public ResponseEntity<ApplicationDto> updateApplication(@RequestBody @Valid ApplicationDto applicationDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(applicationService.update(applicationDto));
    }

    @GetMapping
    public ResponseEntity<List<ApplicationDto>> getApplications(Principal principal) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(applicationService.getAllByOwner(principal.getName()));
    }
}

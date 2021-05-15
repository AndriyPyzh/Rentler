package com.rentler.notification.controller;

import com.rentler.notification.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MailController {
    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/welcome")
    public ResponseEntity<Void> sendWelcome(@RequestParam String email) {
        mailService.welcome(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/new-application")
    public ResponseEntity<Void> sendNewApplication(@RequestParam String username) {
        mailService.newApplication(username);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/application-approved")
    public ResponseEntity<Void> sendApplicationApproved(@RequestParam String username) {
        mailService.applicationApproved(username);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/application-rejected")
    public ResponseEntity<Void> sendApplicationRejected(@RequestParam String username) {
        mailService.applicationRejected(username);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

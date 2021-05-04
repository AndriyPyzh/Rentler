package com.rentler.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MailController {

    private JavaMailSender javaMailSender;

    @Autowired
    public MailController(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @GetMapping
    public String test() {
        return "New Notification!";
    }


    @PostMapping
    public ResponseEntity<Void> sendMail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("andriy.pyzh2412@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        try {
            javaMailSender.send(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

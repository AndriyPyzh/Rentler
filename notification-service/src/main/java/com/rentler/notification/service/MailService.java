package com.rentler.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String mail) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail);

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        try {
            javaMailSender.send(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            throw ex;
        }

        ResponseEntity.status(HttpStatus.OK).build();
    }
}


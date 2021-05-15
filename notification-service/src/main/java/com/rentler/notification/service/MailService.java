package com.rentler.notification.service;

import com.rentler.notification.client.AccountServiceClient;
import com.rentler.notification.dto.AccountDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@SuppressWarnings("ALL")
@Service
public class MailService {

    private final JavaMailSender javaMailSender;
    private final AccountServiceClient accountServiceClient;

    @Autowired
    public MailService(JavaMailSender javaMailSender,
                       AccountServiceClient accountServiceClient) {
        this.javaMailSender = javaMailSender;
        this.accountServiceClient = accountServiceClient;
    }

    public void welcome(String email) {
        String subject = "Welcome!";
        String text = "Welcome to Rentler!\nWe are very glad that you registered in our service.";

        sendMail(email, subject, text);
    }

    public void newApplication(String username) {
        AccountDto account = accountServiceClient.getAccount(username);

        if (account == null) {
            return;
        }

        String subject = String.format("Hello %s %s!", account.getFirstName(), account.getLastName());
        String text = subject + "\nThere is a new application on your property!";

        sendMail(account.getEmail(), subject, text);
    }

    public void applicationApproved(String username) {
        AccountDto account = accountServiceClient.getAccount(username);

        if (account == null) {
            return;
        }

        String subject = String.format("Hello %s %s!", account.getFirstName(), account.getLastName());
        String text = subject + "\nYour application was approved!";

        sendMail(account.getEmail(), subject, text);
    }

    public void applicationRejected(String username) {
        AccountDto account = accountServiceClient.getAccount(username);

        if (account == null) {
            return;
        }

        String subject = String.format("Hello %s %s!", account.getFirstName(), account.getLastName());
        String text = subject + "\nYour application was rejected.";

        sendMail(account.getEmail(), subject, text);
    }

    private void sendMail(String mail, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mail);

        msg.setSubject(subject);
        msg.setText(text);

        javaMailSender.send(msg);
    }

}


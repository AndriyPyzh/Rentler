package com.rentler.account.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 20)
    private String firstName;

    @Column(length = 20)
    private String lastName;

    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$")
    @Column(unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private LocalDateTime dateOfRegistration;
}

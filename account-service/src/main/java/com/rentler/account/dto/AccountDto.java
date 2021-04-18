package com.rentler.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {
    private Long id;

    @Size(min = 3, max = 20)
    @NotBlank
    private String username;

//    @NotBlank
//    @Email(message = "Email should be valid")
//    private String email;

    private String firstName;

    private String lastName;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    private Date lastSeen;

}

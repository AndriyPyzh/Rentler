package com.rentler.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountUpdateDto {

    @NotBlank
    @Length(max = 20)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Can contain only letters")
    private String firstName;

    @NotBlank
    @Length(max = 20)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Can contain only letters")
    private String lastName;

    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Not valid format of phone number")
    private String phoneNumber;

}

package com.rentler.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuccessLoginDto {

    @NotBlank
    private String username;

    @NotBlank
    private String accessToken;

}

package com.rentler.apartment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto {

    private Long id;

    @NotNull
    @Min(1)
    private Integer price;

    @NotNull
    private Long apartmentId;

    private String owner;

    private LocalDate creationDate;
}

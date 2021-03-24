package com.rentler.apartment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentDto {
    private Long id;

    private LocalDate creationDate;

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Min(1)
    private Integer price;

    @Min(1)
    private Integer rooms;

    @Min(0)
    private Integer floor;

    @NotNull
    private AddressDto address;

    @NotNull
    private Long userId;

}

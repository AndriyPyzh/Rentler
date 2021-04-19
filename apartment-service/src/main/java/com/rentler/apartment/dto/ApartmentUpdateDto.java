package com.rentler.apartment.dto;

import com.rentler.apartment.enums.Amenities;
import com.rentler.apartment.enums.ApartmentType;
import com.rentler.apartment.enums.PetPolicy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApartmentUpdateDto {
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    private Long price;

    @NotNull
    private AddressDto address;

    @NotNull
    private ApartmentType type;

    @NotNull
    @Min(0)
    private Integer beds;

    @NotNull
    @Min(0)
    private Integer bath;

    @NotNull
    private Integer floor;

    @NotNull
    @Min(0)
    private Double squareMeters;

    @NotNull
    private PetPolicy petPolicy;

    @NotBlank
    @Length(min = 10, max = 1000)
    private String description;

    private List<Amenities> amenities;

    @NotNull
    private LocalDate availableFrom;
}

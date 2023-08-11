package com.example.virtalStore.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressRequestDto {

    @NotBlank(message = "the street name cannot be empty or null")
    private String street;

    @NotBlank(message = "the state name cannot be empty or null")
    private String state;

    @NotBlank(message = "the neighborhood name cannot be empty or null")
    private String neighborhood;

    @NotBlank(message = "the houseNumber name cannot be empty or null")
    private String houseNumber;
    @NotBlank(message = "the phoneNumber name cannot be empty or null")
    private String phoneNumber;

    @NotBlank(message = "the zipCode name cannot be empty or null")
    private String zipCode;

}

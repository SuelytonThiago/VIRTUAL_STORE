package com.example.virtalStore.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequestDto {

    @NotBlank(message = "the product name cannot be empty or null")
    private String productName;

    @NotNull(message = "the price cannot be null")
    private Double price;

    @NotBlank(message = "the category name cannot be empty or null")
    private String categoryName;
}

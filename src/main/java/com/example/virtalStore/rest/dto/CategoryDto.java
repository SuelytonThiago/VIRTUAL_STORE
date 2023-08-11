package com.example.virtalStore.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CategoryDto {

    @NotBlank(message = "the category name cannot be empty or null")
    private String name;
}

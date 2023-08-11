package com.example.virtalStore.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminDto {

    @NotBlank(message = "the name cannot be empty or null")
    private String name;

    @NotBlank(message = "the cpf cannot be empty or null")
    private String cpf;

    @NotBlank(message = "the email cannot be empty or null")
    private String email;

    @NotBlank(message = "the password cannot be empty or null")
    private String pass;

}

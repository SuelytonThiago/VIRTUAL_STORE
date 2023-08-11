package com.example.virtalStore.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class ClientRequestDto {

    @NotBlank(message = "the name cannot be empty or null")
    private String name;

    @NotBlank(message = "the email cannot be empty or null")
    @Email(message = "insert a valid email")
    private String email;

    @NotBlank(message = "the cpf cannot be empty or null")
    @CPF(message = "insert a valid cpf")
    private String cpf;

    @NotBlank(message = "the password cannot be empty or null")
    private String pass;
}

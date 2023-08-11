package com.example.virtalStore.rest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank(message = "the email cannot be empty or null")
    private String email;

    @NotBlank(message = "the password cannot be empty or null")
    private String pass;
}

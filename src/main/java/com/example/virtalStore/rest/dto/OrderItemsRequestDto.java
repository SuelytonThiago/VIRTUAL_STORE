package com.example.virtalStore.rest.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemsRequestDto {

    @NotNull(message = "the id product cannot be null")
    private Long idProduct;

    @NotNull(message = "the quantity cannot be null")
    private Integer quantity;
}

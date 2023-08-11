package com.example.virtalStore.rest.dto;

import lombok.Data;

@Data
public class OrderItemsResponseDto {

    private String productName;
    private Double price;
    private Integer quantity;
    private Double subtotal;
}

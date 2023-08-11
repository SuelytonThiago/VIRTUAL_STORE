package com.example.virtalStore.rest.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderResponseDto {

    private String instant;
    private String status;
    private Double total;
    private List<OrderItemsResponseDto> items = new ArrayList<>();
}

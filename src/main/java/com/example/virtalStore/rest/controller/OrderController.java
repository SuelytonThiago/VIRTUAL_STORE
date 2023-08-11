package com.example.virtalStore.rest.controller;

import com.example.virtalStore.rest.dto.OrderItemsRequestDto;
import com.example.virtalStore.rest.dto.OrderResponseDto;
import com.example.virtalStore.rest.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    @Operation(summary = "returns all requests from the authenticated client")
    public ResponseEntity<List<OrderResponseDto>> findAllOrdersByClient(HttpServletRequest request){
        return ResponseEntity.ok(orderService.findAllOrders(request));
    }

    @PostMapping
    @Operation(summary = "add a new order")
    public ResponseEntity<Void> insertNewOrder(@RequestBody @Valid List<OrderItemsRequestDto> listDto,
                                                                   HttpServletRequest request){
        orderService.insertNewOrder(listDto,request);
        return ResponseEntity.noContent().build();
    }

}

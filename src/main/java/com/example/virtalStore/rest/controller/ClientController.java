package com.example.virtalStore.rest.controller;

import com.example.virtalStore.rest.dto.AddressRequestDto;
import com.example.virtalStore.rest.dto.ClientRequestDto;
import com.example.virtalStore.rest.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @PostMapping("/newAccount")
    @Operation(summary = "create a new account")
    public ResponseEntity<Void> insertNewClient(@RequestBody @Valid ClientRequestDto clientRequestDto){
        clientService.insertNewClient(clientRequestDto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/addAddress")
    @Operation(summary = "adds a new address to the authenticated client")
    public ResponseEntity<Void> insertNewAddress(@RequestBody @Valid AddressRequestDto addressRequestDto,
                                                                     HttpServletRequest request){
        clientService.insertNewAddress(addressRequestDto,request);
        return ResponseEntity.noContent().build();
    }
}

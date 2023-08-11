package com.example.virtalStore.rest.controller;

import com.example.virtalStore.rest.dto.AdminDto;
import com.example.virtalStore.rest.services.AdministratorService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admins")
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @PostMapping
    @Operation(summary = "Add a new admin")
    public ResponseEntity<Void> insertNewAdmin(@RequestBody @Valid AdminDto adminDto){
        administratorService.insertNewAdmin(adminDto);
        return ResponseEntity.noContent().build();
    }
}

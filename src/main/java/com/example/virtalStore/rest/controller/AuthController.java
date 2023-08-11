package com.example.virtalStore.rest.controller;

import com.example.virtalStore.rest.dto.UserDto;
import com.example.virtalStore.rest.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "perform user authentication with email and password returning a Map with access and update tokens")
    public ResponseEntity<Map<String,String>> login(@RequestBody @Valid UserDto userDto){
        return ResponseEntity.ok(authService.login(userDto));
    }

    @PostMapping("/refresh")
    @Operation(summary = "updates access token via refresh token")
    public ResponseEntity<String> attAccessToken(HttpServletRequest request){
        return ResponseEntity.ok(authService.attAccess_token(request));
    }
}

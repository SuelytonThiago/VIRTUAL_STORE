package com.example.virtalStore.rest.services;

import com.example.virtalStore.configs.security.JWTService;
import com.example.virtalStore.domain.entities.Users;
import com.example.virtalStore.domain.repositories.UsersRepository;
import com.example.virtalStore.rest.dto.UserDto;
import com.example.virtalStore.rest.services.exceptions.CustomException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UsersRepository usersRepository;

    public Map<String,String> login(UserDto userDto){
        Authentication authentication =authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(userDto.getEmail(),userDto.getPass()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var user = (Users)authentication.getPrincipal();

        String access_token = jwtService.generateAccessToken(user);
        String refresh_token = jwtService.generateRefreshToken(user);
        Map<String,String> tokens =new HashMap<>();
        tokens.put("access_token",access_token);
        tokens.put("refresh_token",refresh_token);
        return tokens;
    }

    public String attAccess_token(HttpServletRequest request){
        var auth = request.getHeader("Authorization");
        var token = auth.replace("Bearer ","");
        var email = jwtService.getSubject(token);
        var user = usersRepository.findByEmail(email).get();

        if(jwtService.isTokenValid(token, user)){
            return jwtService.generateAccessToken(user);
        }
        throw new CustomException("invalid Token");
    }
}

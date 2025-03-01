package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.AuthResponseDto;
import com.example.api.dto.LoginDto;
import com.example.api.dto.RegisterDto;
import com.example.api.entity.User;
import com.example.api.service.AuthService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private final AuthService authService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){

        //01 - Receive the token from AuthService
        String token = authService.login(loginDto);

        //02 - Set the token as a response using JwtAuthResponse Dto class
        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setAccessToken(token);

        //03 - Return the response to the user
        return new ResponseEntity<>(authResponseDto, HttpStatus.OK);
    }

    // Build Registr REST API
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDto RegisterDto){
        User registeredUser = authService.register(RegisterDto);

        return ResponseEntity.ok(registeredUser);
    }
}
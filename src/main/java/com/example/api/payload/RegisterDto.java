package com.example.api.payload;

import lombok.Data;

@Data
public class RegisterDto {
    private String name;
    private String email;
    private String password;
    private String role;
}
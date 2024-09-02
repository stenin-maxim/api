package com.example.api.payload;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    //private String usernameOrEmail;
    private String password;
}

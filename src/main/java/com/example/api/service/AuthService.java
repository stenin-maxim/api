package com.example.api.service;

import com.example.api.model.User;
import com.example.api.payload.LoginDto;
import com.example.api.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    User register(RegisterDto RegisterDto);
}

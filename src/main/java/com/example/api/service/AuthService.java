package com.example.api.service;

import com.example.api.dto.LoginDto;
import com.example.api.dto.RegisterDto;
import com.example.api.entity.User;

public interface AuthService {
    String login(LoginDto loginDto);
    User register(RegisterDto RegisterDto);
}

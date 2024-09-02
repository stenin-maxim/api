package com.example.api.service;

import com.example.api.payload.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}

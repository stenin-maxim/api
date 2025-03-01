package com.example.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.api.config.JwtTokenProvider;
import com.example.api.dto.LoginDto;
import com.example.api.dto.RegisterDto;
import com.example.api.entity.Role;
import com.example.api.entity.User;
import com.example.api.enums.RoleEnum;
import com.example.api.repository.RoleRepository;
import com.example.api.repository.UserRepository;
import com.example.api.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public AuthServiceImpl(
        UserRepository userRepository,
        RoleRepository roleRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDto loginDto) {

        // 01 - AuthenticationManager is used to authenticate the user
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getEmail(),
                loginDto.getPassword()
        ));

        /* 02 - SecurityContextHolder is used to allows the rest of the application to know
        that the user is authenticated and can use user data from Authentication object */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 03 - Generate the token based on username and secret key
        String token = jwtTokenProvider.generateToken(authentication);

        // 04 - Return the token to controller
        return token;
    }

    @Override
    public User register(RegisterDto registerDto) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ROLE_USER);

        if (optionalRole.isEmpty()) {
            return null;
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRoles(optionalRole.get());
            
        return userRepository.save(user);
    }
}

package com.example.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.api.model.Ad;
import com.example.api.model.User;
import com.example.api.repository.AdRepository;
import com.example.api.repository.UserRepository;
import com.example.api.service.AdService;

@Component
public class AdServiceImpl implements AdService {
    @Autowired
    private AdRepository adRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Ad createAd(Ad ad) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not exists by Username or Email"));

        ad.setUser(user);

        return adRepository.save(ad);
    }
}

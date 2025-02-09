package com.example.api.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Ad;
import com.example.api.model.Favorites;
import com.example.api.model.User;
import com.example.api.repository.AdRepository;
import com.example.api.repository.FavoritesRepository;
import com.example.api.repository.UserRepository;
import com.example.api.request.FavoritRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/user/favorites", produces = MediaType.APPLICATION_JSON_VALUE)
public class FavoritesController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AdRepository adRepository;

    @Autowired
    FavoritesRepository favoritesRepository;

    @GetMapping(value = "/")
    public ResponseEntity<List<Favorites>> index() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() ->
            new UsernameNotFoundException("User not exists by Username or Email"));

        List<Favorites> favorites = favoritesRepository.findByUserId(user.getId());

        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Favorites> create(@Valid @RequestBody FavoritRequest favoritRequest)  {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not exists by Username or Email"));
    
            Ad ad = adRepository.findById(favoritRequest.getAdId()).orElseThrow(() ->
                new UsernameNotFoundException("Ad not exists by Id"));

            Favorites check = favoritesRepository.exists(user.getId(), ad.getId());

            if (Objects.isNull(check)) {
                Favorites favorites = new Favorites();
                favorites.setUser(user);
                favorites.setAd(ad);
                favoritesRepository.save(favorites);


                return new ResponseEntity<>(favorites, HttpStatus.CREATED);
            }
            
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<Favorites> delete(@PathVariable long id) {
        favoritesRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

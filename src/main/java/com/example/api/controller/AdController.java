package com.example.api.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Ad;
import com.example.api.model.User;
import com.example.api.repository.AdRepository;
import com.example.api.repository.UserRepository;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdController {
    @Autowired
    AdRepository adRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/ads")
    public ResponseEntity<List<Ad>> getAllAds() {
        List<Ad> ads = adRepository.findAll();
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    @GetMapping("/ad/{id}")
    public ResponseEntity<Ad> getAdById(@PathVariable long id) {
        Ad ad = adRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found Ad with id = " + id));
        return new ResponseEntity<>(ad, HttpStatus.OK);
    }

    @PostMapping(value = "/create-ad", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Ad> createNewAd(@RequestBody Ad ad) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not exists by Username or Email"));
                
        ad.setUser(user);
        adRepository.save(ad);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update-ad/{id}")
    public ResponseEntity<Ad> updateAd(@RequestBody Ad newAd, @PathVariable Long id) {
        adRepository.findById(id)
            .map(ad -> {
                ad.setStatus(newAd.getStatus());
                ad.setName(newAd.getName());
                ad.setTypeAd(newAd.getTypeAd());
                ad.setState(newAd.getState());
                ad.setLinkVideo(newAd.getLinkVideo());
                ad.setDescription(newAd.getDescription());
                ad.setPrice(newAd.getPrice());

                return adRepository.save(ad);
            });

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value="/delete-ad/{id}")
    public ResponseEntity<Ad> deleteAd(@PathVariable long id) {
        adRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

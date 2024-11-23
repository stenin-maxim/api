package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Ad;
import com.example.api.repository.AdRepository;
import com.example.api.service.AdService;

@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdController {
    @Autowired
    AdService adService;

    @Autowired
    AdRepository adRepository;

    @GetMapping("/ad/{id}")
    public ResponseEntity<Ad> getAdById(@PathVariable int id) {
        Ad ad = adRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found Ad with id = " + id));
        return new ResponseEntity<>(ad, HttpStatus.OK);
    }

    @PostMapping(value = "/create-ad", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Ad> createNewAd(@RequestBody Ad ad) {
        adService.createAd(ad);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

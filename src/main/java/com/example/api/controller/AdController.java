package com.example.api.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.api.entity.Ad;
import com.example.api.service.AdPhotoService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user/ad", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdController {
    private final AdPhotoService adPhotoService;

    @PostMapping
    public ResponseEntity<Ad> createAd(@RequestParam("data") String jsonObject, @RequestParam MultipartFile[] files) throws IOException {
        adPhotoService.createAd(jsonObject, files);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ad> updateAd(@RequestBody Ad ad, @PathVariable Long id) {
        adPhotoService.updateAd(ad, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ad> deleteAd(@PathVariable Long id) throws IOException {
        adPhotoService.deleteAd(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

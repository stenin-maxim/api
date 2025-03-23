package com.example.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.AdDto;
import com.example.api.entity.Ad;
import com.example.api.entity.Category;
import com.example.api.mapper.AdMapper;
import com.example.api.repository.AdRepository;
import com.example.api.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class PublicController {
    private final CategoryRepository categoryRepository;
    private final AdRepository adRepository;
    private final AdMapper adMapper;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> helloAdmin(){
        return ResponseEntity.ok("Hello Admin");
    }

    @GetMapping("/category")
    public ResponseEntity<Category> listAllCategory() {
        List<Category> categories = this.categoryRepository.findAllCategory();

        return ResponseEntity.ok().body(categories.get(0));
    }

    @GetMapping("/ads")
    public ResponseEntity<List<AdDto>> getAllAds() {
        List<Ad> ads = adRepository.findAll();
        return new ResponseEntity<>(adMapper.toAdDtos(ads), HttpStatus.OK);
    }

}
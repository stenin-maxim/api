package com.example.api.controller;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.AdDto;
import com.example.api.entity.Ad;
import com.example.api.entity.AdPhoto;
import com.example.api.entity.Category;
import com.example.api.mapper.AdMapper;
import com.example.api.repository.AdPhotoRepository;
import com.example.api.repository.AdRepository;
import com.example.api.repository.CategoryRepository;
import com.example.api.service.AdPhotoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
public class PublicController {
    private final CategoryRepository categoryRepository;
    private final AdRepository adRepository;
    private final AdMapper adMapper;
    private final AdPhotoRepository adPhotoRepository;
    private final AdPhotoService adPhotoService;

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

    @GetMapping("/ad/{id}")
    public ResponseEntity<AdDto> getAdById(@PathVariable long id) {
        Ad ad = adPhotoService.getAdById(id);
        return new ResponseEntity<>(adMapper.toAdDto(ad), HttpStatus.OK);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable Long id) throws IOException {
        AdPhoto adPhoto = adPhotoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found AdPhoto with id = " + id));
        String pathImage = adPhoto.getPath() + adPhoto.getName();
        // Path to the image file
        Path path = Paths.get(pathImage);
        // Load the resource
        Resource resource = new UrlResource(path.toUri());
        // Return ResponseEntity with image content type
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }
}
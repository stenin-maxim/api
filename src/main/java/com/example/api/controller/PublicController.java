package com.example.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Category;
import com.example.api.repository.CategoryRepository;

@RestController
@RequestMapping("/api/public")
public class PublicController {
    @Autowired
    private CategoryRepository categoryRepository;

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

}
package com.example.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.ReviewCreateDto;
import com.example.api.dto.ReviewDto;
import com.example.api.mapper.ReviewMapper;
import com.example.api.model.Review;
import com.example.api.service.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user/review", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @GetMapping("/")
    public List<ReviewDto> getAllReviews() {
        List<Review> review = reviewService.getAllReviews();
        return reviewMapper.toDto(review);
    }
    
    @PostMapping
    public ResponseEntity<Review> createReview(@Valid @RequestBody ReviewCreateDto reviewDto) {
        reviewService.createReview(reviewDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

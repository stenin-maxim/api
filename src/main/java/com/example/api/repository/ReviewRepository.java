package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    
}

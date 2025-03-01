package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    
}

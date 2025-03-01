package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.entity.Ad;

public interface AdRepository extends JpaRepository<Ad, Long> {
    
}

package com.example.api.repository;

//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.Ad;

public interface AdRepository extends JpaRepository<Ad, Integer> {
    
}

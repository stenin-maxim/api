package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {}

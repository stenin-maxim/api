package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.UserPhoto;

public interface UserPhotoRepository extends JpaRepository<UserPhoto, Long> {
}

package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.entity.UserPhoto;

public interface UserPhotoRepository extends JpaRepository<UserPhoto, Long> {
}

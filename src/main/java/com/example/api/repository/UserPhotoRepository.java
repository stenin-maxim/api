package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.api.model.UserPhoto;

public interface UserPhotoRepository extends JpaRepository<UserPhoto, Long> {
    @Query("select up from UserPhoto up where up.user_id = :user_id")
    UserPhoto findUserPhoto(Long user_id);
}

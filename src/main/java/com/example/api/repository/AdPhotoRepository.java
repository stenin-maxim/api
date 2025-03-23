package com.example.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.api.entity.AdPhoto;

public interface AdPhotoRepository extends JpaRepository<AdPhoto, Long> {
    @Query(value="SELECT * FROM ad_photos WHERE ad_id = :value", nativeQuery=true)
    List<AdPhoto> findAdPhotos(@Param("value") Long value);
}

package com.example.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.api.entity.Favorites;

public interface FavoritesRepository extends JpaRepository<Favorites, Long> {
    @Query(value = "SELECT f.id, f.ad_id, f.user_id, ads.name, ads.price FROM Favorites f LEFT JOIN ads ON f.ad_id = ads.id WHERE f.user_id = :user_id", nativeQuery = true)
    public List<Favorites> findByUserId(@Param("user_id") Long user_id);

    @Query(value = "SELECT f.* FROM Favorites f WHERE f.user_id = :user_id and f.ad_id = :ad_id", nativeQuery = true)
    public Favorites exists(@Param("user_id") Long user_id, @Param("ad_id") Long ad_id);
}

package com.example.api.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_photos")
public class UserPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name="id")
    private Long id;
    private Long user_id;

    @Column(nullable = false, length = 255)
    private String name;
    private Long size;
    private String path;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public Long getUser() {
        return this.user_id;
    }

    public String getName() {
        return this.name;
    }

    public Long getSize() {
        return size;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Long setUser(Long user_id) {
        this.user_id = user_id;
        return this.user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "Ad {" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", path='" + path + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

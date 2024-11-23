package com.example.api.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name ="user_id", nullable = false, referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @Column(length = 60)
    @NotBlank // проверяет что строка не пуста
    private String name;

    @Column(length = 60)
    @NotBlank
    private String type_ad;

    @Column(length = 60)
    @NotBlank
    private String state;

    @Column(length = 60)
    private String link_video;

    @Column(columnDefinition="text", length = 500)
    private String description;

    private Integer price;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public User getUser(User user) {
        return this.user;
    }

    public User setUser(User user) {
        this.user = user;
        return this.user;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeAd() {
        return this.type_ad;
    }

    public void setTypeAd(String type_ad) {
        this.type_ad = type_ad;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLinkVideo() {
        return this.link_video;
    }

    public void setLinkVideo(String link_video) {
        this.link_video = link_video;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}

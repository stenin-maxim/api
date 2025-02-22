package com.example.api.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(columnDefinition = "boolean default false")
    private Boolean status;

    @Column(length = 60)
    @NotBlank // проверяет что строка не пуста
    private String name;

    @Column(length = 60)
    @NotBlank
    private String typeAd;

    @Column(length = 60)
    @NotBlank
    private String state;

    @Column(length = 60)
    private String linkVideo;

    @Column(columnDefinition="text", length = 500)
    private String description;

    private Integer price;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name ="user_id", nullable = false, referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "ad", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private final List<Favorites> favorites = new ArrayList<>();

    public Ad() {}

    public Long getId() {
        return id;
    }

    public Long getUser() {
        return this.user.getId();
    }

    public User setUser(User user) {
        this.user = user;
        return this.user;
    }

    public List<Favorites> getFavorites() {
        return this.favorites;
    }

    public void setFavorites(Favorites favorites) {
        this.favorites.add(favorites);
    }

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeAd() {
        return this.typeAd;
    }

    public void setTypeAd(String typeAd) {
        this.typeAd = typeAd;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLinkVideo() {
        return this.linkVideo;
    }

    public void setLinkVideo(String linkVideo) {
        this.linkVideo = linkVideo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Ad))
            return false;
        Ad ad = (Ad) o;
            return Objects.equals(this.id, ad.id) && Objects.equals(this.status, ad.status) && Objects.equals(this.name, ad.name)
                && Objects.equals(this.typeAd, ad.typeAd) && Objects.equals(this.state, ad.state) && Objects.equals(this.linkVideo, ad.linkVideo) 
                && Objects.equals(this.description, ad.description) && Objects.equals(this.price, ad.price) && Objects.equals(this.createdAt, ad.createdAt)
                && Objects.equals(this.updatedAt, ad.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.status, this.name, this.typeAd, this.state, this.linkVideo, this.description, this.price, this.createdAt, this.updatedAt);
    }

    @Override
    public String toString() {
        return "Ad {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", typeAd='" + typeAd + '\'' +
                ", state='" + state + '\'' +
                ", linkVideo='" + linkVideo + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

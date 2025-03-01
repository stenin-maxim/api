package com.example.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="favorites")
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonIgnore
    private Ad ad;

    public Favorites() {}

    public Favorites(User user, Ad ad) {
        this.user = user;
        this.ad = ad;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Ad getAd() {
        return this.ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    @Override
    public String toString() {
        return "Favorites {" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", ad='" + ad + '\'' +
                '}';
    }

}

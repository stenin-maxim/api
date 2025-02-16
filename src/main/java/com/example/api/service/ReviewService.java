package com.example.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.dto.ReviewDto;
import com.example.api.exception.NotFoundException;
import com.example.api.model.Ad;
import com.example.api.model.Review;
import com.example.api.model.User;
import com.example.api.repository.AdRepository;
import com.example.api.repository.ReviewRepository;
import com.example.api.repository.UserRepository;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdRepository adRepository;

    public void create(ReviewDto reviewDto) {
        User user = userRepository.findById(reviewDto.getUserId())
            .orElseThrow(() -> new NotFoundException("Пользователь не найден!"));
        Ad ad = adRepository.findById(reviewDto.getAdId())
            .orElseThrow(() -> new NotFoundException("Обьявление не найдено!"));

        Review review = new Review();
        review.setUser(user);
        review.setAd(ad);
        review.setRating(reviewDto.getRating());
        review.setText(reviewDto.getText());

        reviewRepository.save(review);
    }
}

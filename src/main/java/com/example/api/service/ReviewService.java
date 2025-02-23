package com.example.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.api.dto.ReviewCreateDto;
import com.example.api.exception.NotFoundException;
import com.example.api.model.Ad;
import com.example.api.model.Review;
import com.example.api.model.User;
import com.example.api.repository.AdRepository;
import com.example.api.repository.ReviewRepository;
import com.example.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final AdRepository adRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public void createReview(ReviewCreateDto reviewDto) {
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

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}

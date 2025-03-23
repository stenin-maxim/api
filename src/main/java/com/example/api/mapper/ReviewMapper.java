package com.example.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.api.dto.ReviewDto;
import com.example.api.entity.Review;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    public List<ReviewDto> toReviewDtos(List<Review> review);
}

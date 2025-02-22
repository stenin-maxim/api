package com.example.api.mapper;

import java.util.List;
import org.mapstruct.Mapper;

import com.example.api.dto.ReviewResponseDto;
import com.example.api.model.Review;


@Mapper(componentModel = "spring")
public interface ReviewMapper {
    public List<ReviewResponseDto> toDto(List<Review> review);
}

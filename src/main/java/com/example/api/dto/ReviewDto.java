package com.example.api.dto;

import java.time.LocalDate;

import com.example.api.entity.Ad;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewDto {
    private Long id;
    private UserDto user;
    private Ad ad;
    private String text;
    private String rating;
    private LocalDate createdAt;
}

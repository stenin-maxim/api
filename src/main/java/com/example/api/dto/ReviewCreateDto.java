package com.example.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewCreateDto {
    private Long userId;
    private Long adId;
    private Byte rating;
    private String text;
}

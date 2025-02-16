package com.example.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder
public class ReviewDto {
    private Long userId;
    private Long adId;
    private Byte rating;
    private String text;
}

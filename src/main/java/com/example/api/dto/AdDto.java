package com.example.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdDto {
    private Long id;
    private String title;
    private String typeAd;
    private Integer price;
    private String location;
    private List<AdPhotoDto> adPhotos;
    private LocalDateTime createdAt;
}

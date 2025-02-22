package com.example.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserPhotoDto {
    private Long id;
    private String name;
    private String path;
}

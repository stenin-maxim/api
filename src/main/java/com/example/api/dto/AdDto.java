package com.example.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AdDto {
    private Long id;
    private String name;
    private Integer price;
}

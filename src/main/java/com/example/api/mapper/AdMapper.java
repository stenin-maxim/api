package com.example.api.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.example.api.dto.AdDto;
import com.example.api.entity.Ad;

@Mapper(componentModel = "spring")
public interface AdMapper {
    public List<AdDto> toAdDtos(List<Ad> posts);
}

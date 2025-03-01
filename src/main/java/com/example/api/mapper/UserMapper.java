package com.example.api.mapper;

import org.mapstruct.Mapper;

import com.example.api.dto.UserDto;
import com.example.api.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public UserDto toDto(User user);
}

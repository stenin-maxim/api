package com.example.api.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.example.api.model.UserPhoto;

public interface FileStorageService {
    public UserPhoto upload(MultipartFile file) throws IOException;
    public void delete(Long fileId) throws IOException;
}

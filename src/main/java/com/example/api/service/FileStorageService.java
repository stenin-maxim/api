package com.example.api.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    public String saveFile(MultipartFile file);

    public void delete();
}

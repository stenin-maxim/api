package com.example.api.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.api.exception.FileStorageException;
import com.example.api.model.User;
import com.example.api.repository.UserRepository;
import com.example.api.service.FileStorageService;

@Service
public class FilesStorageServiceImpl implements FileStorageService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public String saveFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Проверяем, содержит ли имя файла недопустимые символы.
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            if (!Files.exists(this.createDirPath())) {
                Files.createDirectories(this.createDirPath());
            }

            String fileCode = RandomStringUtils.randomAlphanumeric(8);
            Path filePath = this.createDirPath().resolve(fileCode + "-" + fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {       
            throw new FileStorageException("Could not save file: " + fileName, ioe);
        }
         
        return fileName;
    }

    private Path createDirPath() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = (User) userRepo.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not exists by Username or Email"));

        String userIdString = Long.toString(user.getId());

        Path dirPath = Paths.get("uploads/user/" + userIdString +"/avatar/");

        return dirPath;
    }
  
    @Override
    public void delete() {
       //FileSystemUtils.deleteRecursively(dirPath.toFile());
    }
}

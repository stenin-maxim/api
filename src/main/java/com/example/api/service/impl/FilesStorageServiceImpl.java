package com.example.api.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.api.entity.User;
import com.example.api.entity.UserPhoto;
import com.example.api.exception.FileStorageException;
import com.example.api.repository.UserPhotoRepository;
import com.example.api.repository.UserRepository;
import com.example.api.service.FileStorageService;

@Service
public class FilesStorageServiceImpl implements FileStorageService {
    private final UserRepository userRepo;
    private final UserPhotoRepository userPhotoRepo;

    public FilesStorageServiceImpl(UserRepository userRepo, UserPhotoRepository userPhotoRepo) {
        this.userRepo = userRepo;
        this.userPhotoRepo = userPhotoRepo;
    }

    @Transactional(rollbackFor = {IOException.class})
    @Override
    public UserPhoto upload(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (fileName.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = (User) userRepo.findByEmail(email).orElseThrow(() ->
            new UsernameNotFoundException("User not exists by Username or Email"));
        
        String fileCode = RandomStringUtils.randomAlphanumeric(8);
        String newFileName = fileCode + "-" + fileName;
        UserPhoto userPhoto = new UserPhoto();
        UserPhoto userPhotoObj = user.getUserPhoto();
        String path = "uploads/user/" + Long.toString(user.getId()) + "/avatar/";
        Path filePath = Paths.get(path).resolve(newFileName);

        if (userPhotoObj == null) {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    
            userPhoto.setUser(user);
            userPhoto.setName(newFileName);
            userPhoto.setSize(file.getSize());
            userPhoto.setPath(path);
    
            return userPhotoRepo.save(userPhoto);
        }

        Path filePath2 = Paths.get(userPhotoObj.getPath() + userPhotoObj.getName());
        System.out.println(Files.deleteIfExists(filePath2));
    
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        userPhotoObj.setName(newFileName);
        userPhotoObj.setSize(file.getSize());
        return userPhotoRepo.save(userPhotoObj);
    }

    @Override
    public void delete(Long userPhotoId) throws IOException {
        UserPhoto userPhoto = userPhotoRepo.findById(userPhotoId).orElseThrow(() ->
            new UsernameNotFoundException("User not exists by ID"));
        Path filePath = Paths.get(userPhoto.getPath() + userPhoto.getName());

        Files.deleteIfExists(filePath);
        userPhotoRepo.delete(userPhoto);
    }
}

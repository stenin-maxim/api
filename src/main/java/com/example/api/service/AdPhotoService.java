package com.example.api.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.api.entity.Ad;
import com.example.api.entity.AdPhoto;
import com.example.api.entity.User;
import com.example.api.exception.FileStorageException;
import com.example.api.repository.AdPhotoRepository;
import com.example.api.repository.AdRepository;
import com.example.api.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdPhotoService {
    private final AdRepository adRepository;
    private final UserRepository userRepository;
    private final AdPhotoRepository adPhotoRepository;
    private final ObjectMapper objectMapper;

    public Ad getAdById(Long id) {
        Ad ad = adRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found Ad with id = " + id));
        return ad;
    }

    public void createAd(String jsonObject, @RequestParam MultipartFile[] files) throws IOException {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userRepository.findByEmail(email).orElseThrow(() ->
                    new UsernameNotFoundException("User not exists by Username or Email"));
            Ad ad;
    
            ad = objectMapper.readValue(jsonObject, Ad.class);
            ad.setUser(user);
            adRepository.save(ad);

            String path = "uploads/user/" + Long.toString(user.getId()) + "/ads/" + ad.getId() + "/";

            if (!Files.exists(Paths.get(path))) {
                Files.createDirectories(Paths.get(path));
            }

            for (MultipartFile file : files) {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());

                if (fileName.contains("..")) {
                    throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                }
                Path fileNameAndPath = Paths.get(path, file.getOriginalFilename());
                Files.copy(file.getInputStream(), fileNameAndPath, StandardCopyOption.REPLACE_EXISTING);

                AdPhoto adPhoto = new AdPhoto();

                adPhoto.setAd(ad);
                adPhoto.setName(fileName);
                adPhoto.setSize(file.getSize());
                adPhoto.setPath(path);

                adPhotoRepository.save(adPhoto);
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void updateAd(Ad newAd, Long id) {
        adRepository.findById(id)
            .map(ad -> {
                ad.setStatus(newAd.getStatus());
                ad.setTitle(newAd.getTitle());
                ad.setTypeAd(newAd.getTypeAd());
                ad.setState(newAd.getState());
                ad.setLinkVideo(newAd.getLinkVideo());
                ad.setDescription(newAd.getDescription());
                ad.setPrice(newAd.getPrice());
                ad.setLocation(newAd.getLocation());

                return adRepository.save(ad);
            });
    }


    public void deleteAd(Long adId) throws IOException {
        List<AdPhoto> adPhotos = adPhotoRepository.findAdPhotos(adId);

        for (AdPhoto adPhoto: adPhotos) {
            Path filePath = Paths.get(adPhoto.getPath() + adPhoto.getName());
            Files.deleteIfExists(filePath);
        }

        Files.delete(Paths.get(adPhotos.get(0).getPath()));
        adRepository.deleteById(adId);
    }
}

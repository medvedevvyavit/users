package ru.development.users.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoService {

    String savePhoto(MultipartFile file);
    boolean isPhotoNotExists(String uri);
}

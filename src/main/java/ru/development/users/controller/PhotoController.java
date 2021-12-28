package ru.development.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.development.users.service.PhotoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/photo")
public class PhotoController {

    private final PhotoService service;

    @PostMapping
    public ResponseEntity<String> savePhoto(@RequestParam("file") MultipartFile file){
        return new ResponseEntity<>(service.savePhoto(file), HttpStatus.OK);
    }
}

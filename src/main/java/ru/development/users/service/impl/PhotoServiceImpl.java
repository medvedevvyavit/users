package ru.development.users.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.development.users.exception.ExceptionConstants;
import ru.development.users.exception.FileException;
import ru.development.users.service.PhotoService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    @Value("${default.photo.catalog}")
    private String path;

    @Override
    public String savePhoto(MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                Path filePath = Paths.get(path + "/" + file.getOriginalFilename());
                if (isPhotoNotExists(filePath.toString())) {
                    file.transferTo(filePath.toAbsolutePath().toFile());
                }
                return filePath.toString();
            } catch (IOException e) {
                throw new FileException(
                        String.format(
                                e.getMessage(),
                                file.getOriginalFilename()
                        )
                );
            }
        }
        throw new FileException(
                String.format(
                        ExceptionConstants.FILE_IS_EMPTY_MSG,
                        file.getOriginalFilename()
                )
        );
    }

    @Override
    public boolean isPhotoNotExists(String uri) {
        return !new File(uri).exists();
    }
}

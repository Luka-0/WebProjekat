package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImageUploadService {

    private String uploadFolderPath = "E:\\Fax\\II\\VebProjekat\\artikal-photos";

    public void uploadToLocal(MultipartFile multipartFile) {
        try {

            byte[] data =  multipartFile.getBytes();
            Path path = Paths.get(uploadFolderPath + multipartFile.getOriginalFilename());
            Files.write(path, data);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

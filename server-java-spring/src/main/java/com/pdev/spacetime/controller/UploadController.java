package com.pdev.spacetime.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@RestController
public class UploadController {


    @PostMapping(value = "/uploads", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> saveFile(@RequestParam MultipartFile file) throws IOException {
        String fileName = generateFileName(Objects.requireNonNull(file.getOriginalFilename()));
        String path = System.getProperty("user.dir") + "\\uploads\\";

        String fullPath = path + fileName;

        Files.copy(file.getInputStream(), Path.of(fullPath), StandardCopyOption.REPLACE_EXISTING);

        return ResponseEntity.ok("{ \"fileUrl\": \"" + fullPath + "\"}");
    }

    private String generateFileName(String originalFileName) {
        int i = originalFileName.lastIndexOf(".");
        return UUID.randomUUID() + "." + originalFileName.substring(i + 1);
    }
}

package com.pdev.spacetime.controller;

import com.pdev.spacetime.controller.request.MemoryRequest;
import com.pdev.spacetime.dto.MemoryDto;
import com.pdev.spacetime.repository.MemoryRepository;
import com.pdev.spacetime.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class MemoryController {

    private final MemoryService service;

    @GetMapping("/memories/{id}")
    public ResponseEntity<MemoryDto> findOne(@PathVariable Long id) {
        return service.findMemory(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/memories")
    public List<MemoryDto> findAll() {
        return service.findMemories();
    }

    @PostMapping("/memories")
    public ResponseEntity<Void> saveMemory(@RequestParam MultipartFile file, @RequestParam String content, @RequestParam boolean isPublic) {
        try {

            MemoryRequest request = new MemoryRequest();
            request.setContent(content);
            request.setPublic(isPublic);

            if(Objects.nonNull(file.getOriginalFilename())) {
                request.setImage(file.getBytes());
            }

            service.saveMemory(request);

        } catch (IOException e) {
            System.out.println(e.getMessage());
            ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }
}

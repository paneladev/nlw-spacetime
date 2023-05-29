package com.pdev.spacetime.controller;

import com.pdev.spacetime.controller.request.MemoryRequest;
import com.pdev.spacetime.dto.MemoryDto;
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
        return service.findMemoriesByUser();
    }

    @PostMapping("/memories/upload")
    public ResponseEntity<Long> saveMemoryUpload(@RequestBody MultipartFile file) {
        try {
            if (Objects.nonNull(file.getOriginalFilename())) {
                Long aLong = service.saveMemoryImage(file.getBytes());
                return ResponseEntity.ok(aLong);
            }
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.notFound().build();
    }


    @PostMapping("/memories")
    public ResponseEntity<Void> saveMemory(@RequestBody MemoryRequest memoryRequest) {
        service.saveOrUpdateMemory(memoryRequest);
        return ResponseEntity.ok().build();
    }
}

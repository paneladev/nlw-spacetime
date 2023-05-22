package com.pdev.spacetime.controller;

import com.pdev.spacetime.dto.MemoryDto;
import com.pdev.spacetime.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}

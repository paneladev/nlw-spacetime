package com.pdev.spacetime.service;

import com.pdev.spacetime.controller.request.MemoryRequest;
import com.pdev.spacetime.dto.MemoryDto;
import com.pdev.spacetime.entity.Memory;
import com.pdev.spacetime.entity.User;
import com.pdev.spacetime.repository.MemoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemoryService {

    private final MemoryRepository memoryRepository;

    @Transactional
    public Optional<MemoryDto> findMemory(Long id) {
        Optional<Memory> optMemory = memoryRepository.findById(id);

        if (optMemory.isEmpty()) {
            return Optional.empty();
        }

        MemoryDto dto = MemoryDto.toDto(optMemory.get());
        return Optional.of(dto);
    }

    @Transactional
    public List<MemoryDto> findMemories() {
        List<Memory> memories = memoryRepository.findAll();

        return memories.stream()
                .map(MemoryDto::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveMemory(MemoryRequest memoryRequest) {
        Memory memory = new Memory();
        memory.setContent(memoryRequest.getContent());
        //memory.setCoverUrl(memoryRequest.getCoverUrl());
        memory.setPublic(memoryRequest.isPublic());
        memory.setCreatedAt(LocalDateTime.now());
        memory.setCoverImage(memoryRequest.getImage());

        // receber o usuario dinamicamente
        memory.setUser(new User(2L));

        memoryRepository.save(memory);
    }
}

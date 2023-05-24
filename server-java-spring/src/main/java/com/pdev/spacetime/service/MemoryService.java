package com.pdev.spacetime.service;

import com.pdev.spacetime.config.LoggedUserConfig;
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
    private final LoggedUserConfig loggedUserConfig;

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
    public List<MemoryDto> findMemoriesByUser() {
        List<Memory> memories = memoryRepository.findByUserOrIsPublicTrue(new User(loggedUserConfig.loggedUser().getId()));

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
        memory.setUser(new User(loggedUserConfig.loggedUser().getId()));

        memoryRepository.save(memory);
    }
}

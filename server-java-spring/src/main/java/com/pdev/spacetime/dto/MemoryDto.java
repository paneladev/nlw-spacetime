package com.pdev.spacetime.dto;

import com.pdev.spacetime.entity.Memory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class MemoryDto {

    private Long id;
    private String coverUrl;
    private String excerpt;
    private LocalDateTime createdAd;

    public static MemoryDto toDto(Memory memory) {
        return MemoryDto.builder()
                .id(memory.getId())
                .coverUrl(memory.getCoverUrl())
                .excerpt(memory.getContent())
                .createdAd(memory.getCreatedAt())
                .build();

    }
}

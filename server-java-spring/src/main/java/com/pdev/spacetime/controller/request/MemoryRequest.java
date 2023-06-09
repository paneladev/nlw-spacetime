package com.pdev.spacetime.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemoryRequest {

    private Long memoryId;
    private String content;
    private boolean isPublic;
}

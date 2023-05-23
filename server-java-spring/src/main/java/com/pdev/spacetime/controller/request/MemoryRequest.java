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

    private String content;
    private String coverUrl;
    private boolean isPublic;
    private byte[] image;
}

package com.pdev.spacetime.controller.response;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResponse {

    private String token;
}

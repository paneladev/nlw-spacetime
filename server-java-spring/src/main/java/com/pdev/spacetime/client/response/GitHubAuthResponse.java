package com.pdev.spacetime.client.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GitHubAuthResponse {

    private String access_token;
    private String scope;
    private String token_type;
}

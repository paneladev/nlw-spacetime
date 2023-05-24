package com.pdev.spacetime.client.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GitHubUserResponse {
    private Long id;
    private String login;
    private String name;
    private String avatar_url;
}

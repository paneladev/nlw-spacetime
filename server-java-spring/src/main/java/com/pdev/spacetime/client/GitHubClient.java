package com.pdev.spacetime.client;

import com.pdev.spacetime.client.response.GitHubUserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "GitHubClient", url = "https://api.github.com/user")
public interface GitHubClient {

    @GetMapping
    GitHubUserResponse getUser(@RequestHeader("Authorization") String bearerToken);
}

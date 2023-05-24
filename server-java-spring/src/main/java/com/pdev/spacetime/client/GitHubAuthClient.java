package com.pdev.spacetime.client;

import com.pdev.spacetime.client.response.GitHubAuthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "GitHubAuthClient", url = "https://github.com/")
public interface GitHubAuthClient {

    @PostMapping(path = "login/oauth/access_token", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    GitHubAuthResponse getAcessToken(@RequestParam String client_id, @RequestParam String client_secret, @RequestParam String code);
}

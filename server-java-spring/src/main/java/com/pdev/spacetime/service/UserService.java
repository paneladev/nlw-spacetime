package com.pdev.spacetime.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.pdev.spacetime.client.GitHubAuthClient;
import com.pdev.spacetime.client.GitHubClient;
import com.pdev.spacetime.client.response.GitHubAuthResponse;
import com.pdev.spacetime.client.response.GitHubUserResponse;
import com.pdev.spacetime.config.ApplicationProperties;
import com.pdev.spacetime.controller.request.AuthRequest;
import com.pdev.spacetime.controller.response.UserRegisterResponse;
import com.pdev.spacetime.entity.User;
import com.pdev.spacetime.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ApplicationProperties appProperties;

    private final UserRepository userRepository;

    private final GitHubAuthClient gitHubAuthClient;
    private final GitHubClient gitHubClient;

    @Transactional
    public User findDetailedUser(Long id) {
        return userRepository.findDetailedById(id);
    }

    public Optional<UserRegisterResponse> registerUser(AuthRequest authRequest) {
        try {
            GitHubAuthResponse gitHubAuthResponse = gitHubAuthClient.getAcessToken(appProperties.getGitHubClientId(), appProperties.getGitHubClientSecret(), authRequest.getCode());
            GitHubUserResponse gitHubUserResponse = gitHubClient.getUser("Bearer ".concat(gitHubAuthResponse.getAccess_token()));

            Optional<User> optUser = this.findUserByGitHubId(gitHubUserResponse.getId());

            User user = optUser.orElseGet(() -> userRepository.save(User
                    .builder()
                    .gitHubId(gitHubUserResponse.getId())
                    .name(gitHubUserResponse.getName())
                    .login(gitHubUserResponse.getLogin())
                    .avatarUrl(gitHubUserResponse.getAvatar_url())
                    .build()));

            UserRegisterResponse userRegisterResponse = UserRegisterResponse.builder()
                    .token(this.generateAccessToken(user))
                    .build();

            return Optional.of(userRegisterResponse);

        } catch (Exception e) {
            // TODO adicionar logs
            return Optional.empty();
        }
    }

    private String generateAccessToken(User user) {
        return JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("name", user.getName())
                .withClaim("avatarUrl", user.getAvatarUrl())
                .withExpiresAt(LocalDateTime.now().plusDays(10).toInstant(ZoneOffset.UTC))
                .sign(Algorithm.HMAC256(appProperties.getJwtSecret()));
    }

    private Optional<User> findUserByGitHubId(Long gitHubId) {
        return userRepository.findByGitHubId(gitHubId);
    }

}

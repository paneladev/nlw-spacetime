package com.pdev.spacetime.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class AuthorizationFilterConfig extends OncePerRequestFilter {

    private final LoggedUserConfig loggedUserConfig;
    private final ApplicationProperties applicationProperties;

    private static final String BEARER = "Bearer ";

    private static final String[] AUTH_WHITELIST = {"/register"};

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (Strings.isNotBlank(authorizationHeader) && authorizationHeader.startsWith(BEARER)) {
            try {
                String token = authorizationHeader.substring(BEARER.length());
                Algorithm algorithm = Algorithm.HMAC256(applicationProperties.getJwtSecret());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT verify = verifier.verify(token);

                LoggedUser loggedUser = loggedUserConfig.loggedUser();
                loggedUser.setId(Long.parseLong(verify.getSubject()));
                loggedUser.setName(verify.getClaim("name").asString());

                filterChain.doFilter(request, response);
            } catch (Exception ex) {
                refuseRequest(response);
            }
        } else {
            if (!Arrays.asList(AUTH_WHITELIST).contains(request.getRequestURI())) {
                refuseRequest(response);
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

    private void refuseRequest(HttpServletResponse response) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
    }
}

package com.pdev.spacetime.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggedUserConfig {

    @Bean
    public LoggedUser loggedUser() {
        return new LoggedUser();
    }
}

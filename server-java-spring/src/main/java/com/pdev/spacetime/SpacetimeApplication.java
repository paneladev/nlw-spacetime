package com.pdev.spacetime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpacetimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpacetimeApplication.class, args);
    }

}

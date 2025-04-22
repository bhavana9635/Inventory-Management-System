package com.possession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PossessionManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(PossessionManagerApplication.class, args);
    }
} 
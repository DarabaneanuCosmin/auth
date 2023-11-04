package com.app.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class UtilsConfig {
    @Bean
    public Random random() {
        return new Random();
    }
}

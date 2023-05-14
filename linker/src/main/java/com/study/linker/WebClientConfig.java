package com.study.linker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    private static final String URL = "http://localhost:1111";

    @Bean
    public WebClient webClient() {
        return WebClient.create(URL);
    }
}

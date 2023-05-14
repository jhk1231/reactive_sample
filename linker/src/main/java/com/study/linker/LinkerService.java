package com.study.linker;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class LinkerService {

    private final WebClient webClient;

    public LinkerService(WebClient webClient) {
        this.webClient = webClient;
    }


    Mono<String> getEco(String message) {
        return webClient.get()
                .uri("/eco?msg=" + message)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class)
                .flatMap(entity -> Mono.just(entity.getBody()));
    }
}

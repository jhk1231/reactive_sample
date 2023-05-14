package com.study.linker;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class EcoClient {

    private final WebClient webClient;

    public EcoClient(WebClient webClient) {
        this.webClient = webClient;
    }

    Mono<ResponseEntity<String>> getEcoMessage(String message) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/eco")
                        .queryParam("msg", message)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class);
    }

//    Mono<ResponseEntity<String>> getEcoMessage(String message) {
//        return webClient.get()
//                .uri("/eco?msg=" + message)
//                .accept(MediaType.APPLICATION_JSON)
//                .retrieve()
//                .toEntity(String.class);
//    }
}

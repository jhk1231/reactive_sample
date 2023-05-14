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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class LinkerService {

    private final EcoClientService ecoClientService;

    public LinkerService(EcoClientService ecoClientService) {
        this.ecoClientService = ecoClientService;
    }

    Mono<List<String>> getEco(Flux<String> messages) {
        Flux<Mono<String>> responseFlux = messages.map(message -> ecoClientService.getEcoMessage(message));
        return Flux.merge(responseFlux).collectList();
    }
}

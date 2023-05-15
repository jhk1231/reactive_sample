package com.study.linker.service;

import com.study.linker.external.EcoClientService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class LinkerService {

    private final EcoClientService ecoClientService;

    public LinkerService(EcoClientService ecoClientService) {
        this.ecoClientService = ecoClientService;
    }

    public Mono<List<String>> getEco(Flux<String> messages) {
        Flux<Mono<String>> responseFlux = messages.map(message -> ecoClientService.getEcoMessage(message));
        return Flux.merge(responseFlux).collectList();
    }
}

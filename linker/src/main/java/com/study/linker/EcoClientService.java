package com.study.linker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EcoClientService {

    private final EcoClient ecoClient;

    public EcoClientService(EcoClient ecoClient) {
        this.ecoClient = ecoClient;
    }

    Mono<String> getEcoMessage(String message) {
        return ecoClient.getEcoMessage(message)
                .map(data -> {
                    validataionEcoMessage(data);
                    return data.getBody();
                });
    }

    private void validataionEcoMessage(ResponseEntity<String> data) {
        if (HttpStatus.OK != data.getStatusCode()) {
            throw new RuntimeException("[API 호출 에러]");
        }
    }
}

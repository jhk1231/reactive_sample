package com.study.linker.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.study.linker.service.LinkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping
@RestController
@Slf4j
public class LinkerController {

    private final LinkerService linkerService;

    public LinkerController(LinkerService linkerService) {
        this.linkerService = linkerService;
    }

    @PostMapping("/link")
    public Mono<ResponseEntity<Map<String, Object>>>
    getMerge(@RequestBody BodyItem body) {

        log.info("Request to get-message. [messages:{}]", body.getMessages());

        final Flux<String> messageFlux = Flux.fromIterable(body.getMessages());
        final Mono<List<String>> data = linkerService.getEco(messageFlux);
        final Mono<ResponseEntity<Map<String, Object>>> responseDto = createResponseDto(data);

        return responseDto;
    }

    private Mono<ResponseEntity<Map<String, Object>>> createResponseDto(Mono<List<String>> data) {
        return data.map(item -> {
            Map<String, Object> result = new HashMap<>();
            result.put("result", item);
            log.info("Response to get-message. [messages:{}]", item);
            return ResponseEntity.ok(result);
        });
    }

    static class BodyItem {

        @JsonProperty("messages")
        private List<String> messages;

        public BodyItem() {
        }

        public List<String> getMessages() {
            return messages;
        }

        public void setMessages(List<String> messages) {
            this.messages = messages;
        }
    }

}

package com.study.linker;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping
@RestController
public class LinkerController {

    private static final String URL = "http://localhost:1111";
    private static Map<String, Object> rxMap = new HashMap<>();


    @PostMapping("/link")
    public ResponseEntity<Map<String, Object>>
    getMerge(@RequestBody BodyItem body) {

        Mono<ResponseEntity<String>> mono = WebClient.create().get()
                .uri(
                        "http://localhost:1111/eco?msg=hi"
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(String.class);

        mono.subscribe(item -> saveItem(item.getBody(), body.getUser()));


        Map<String, Object> result = new HashMap<>();
//        result.put("result", List.of(resultString.getBody()));

        return ResponseEntity.ok(result);
    }

    static void saveItem(String item, String user) {
        List<String> list = (List<String>) rxMap.get(user);
        list.add(item);
        rxMap.put(user, list);
    }

    static class BodyItem {

        @JsonProperty("user")
        private String user;

        @JsonProperty("messages")
        private List<String> messages;

        public BodyItem() {
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public List<String> getMessages() {
            return messages;
        }

        public void setMessages(List<String> messages) {
            this.messages = messages;
        }
    }

}

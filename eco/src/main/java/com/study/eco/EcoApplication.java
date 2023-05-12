package com.study.eco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class EcoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcoApplication.class, args);
    }


    @GetMapping("/eco")
    public String eco(@RequestParam("msg") String message) {
        try {
            Thread.sleep(1_000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "success_" + message;
    }

}

package com.github.smsilva.wasp.demo.springsecuritydemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ExampleApi {

    @GetMapping("/public")
    ResponseEntity<Void> publicRoute() {
        return ResponseEntity
                .ok()
                .header("extra-message", "public")
                .build();
    }

    @GetMapping("/private")
    ResponseEntity<Void> privateRoute() {
        return ResponseEntity
                .ok()
                .header("extra-message", "private")
                .build();
    }

    @PostMapping("/save")
    ResponseEntity<Void> save() {
        return ResponseEntity
                .ok()
                .header("extra-message", "save")
                .build();
    }

}

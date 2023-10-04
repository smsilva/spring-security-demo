package com.github.smsilva.wasp.demo.springsecuritydemo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api")
public class Auth0Api {

    @GetMapping("/public")
    ResponseEntity<Void> publicRoute(HttpServletRequest request) {
        return ResponseEntity
                .ok()
                .header("extra-message", "public")
                .build();
    }

    @GetMapping("/private")
    ResponseEntity<Void> privateRoute(HttpServletRequest request) {
        return ResponseEntity
                .ok()
                .header("extra-message", "private")
                .build();
    }

    @PostMapping("/save")
    ResponseEntity<Void> save(HttpServletRequest request) {
        return ResponseEntity
                .ok()
                .header("extra-message", "save")
                .build();
    }

}

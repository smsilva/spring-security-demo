package com.github.smsilva.wasp.demo.springsecuritydemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ExampleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleController.class);

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

    @GetMapping("/jwt-test")
    ResponseEntity<?> privateRouteWithToken(final JwtAuthenticationToken jwtAuthenticationToken) {
        if (jwtAuthenticationToken == null) {
            ResponseEntity
                    .badRequest()
                    .build();
        }

        LOGGER.info("jwtAuthenticationToken = {}", jwtAuthenticationToken);
        LOGGER.info("jwtAuthenticationToken.toString() = {}", jwtAuthenticationToken.toString());

        return ResponseEntity
                .ok(jwtAuthenticationToken);
    }

    @PostMapping("/save")
    ResponseEntity<Void> save() {
        return ResponseEntity
                .ok()
                .header("extra-message", "save")
                .build();
    }

}

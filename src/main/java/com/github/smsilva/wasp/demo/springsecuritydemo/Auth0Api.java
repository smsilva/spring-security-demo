package com.github.smsilva.wasp.demo.springsecuritydemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api")
public class Auth0Api {

    private static final Logger LOGGER = LoggerFactory.getLogger(Auth0Api.class);

    @GetMapping("/public")
    ResponseEntity<Void> publicRequest(HttpServletRequest request) {
        LOGGER.info("Received request: {}", request);

        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            LOGGER.info("{}: {}", headerName, request.getHeader(headerName));
        });

        return ResponseEntity
                .ok()
                .header("extra-message", "public")
                .build();
    }

    @GetMapping("/private")
    ResponseEntity<Void> privateRequest(HttpServletRequest request) {
        LOGGER.info("Received request: {}", request);

        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            LOGGER.info("{}: {}", headerName, request.getHeader(headerName));
        });

        return ResponseEntity
                .ok()
                .header("extra-message", "private")
                .build();
    }

    @GetMapping("/public")
    ResponseEntity<String> publicRoute(HttpServletRequest request) {
        LOGGER.info("Received request: {}", request);

        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            LOGGER.info("{}: {}", headerName, request.getHeader(headerName));
        });

        return ResponseEntity
                .ok()
                .header("extra-message", "hi")
                .build();
    }

    @GetMapping("/private")
    ResponseEntity<String> privateRoute(HttpServletRequest request) {
        LOGGER.info("Received request: {}", request);

        request.getHeaderNames().asIterator().forEachRemaining(headerName -> {
            LOGGER.info("{}: {}", headerName, request.getHeader(headerName));
        });

        return ResponseEntity
                .ok()
                .header("extra-message", "hi")
                .build();
    }

}

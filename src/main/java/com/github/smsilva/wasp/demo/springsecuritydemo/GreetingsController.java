package com.github.smsilva.wasp.demo.springsecuritydemo;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("greetings")
public class GreetingsController {

    @GetMapping("hi")
	public ResponseEntity<String> hi() {
        String message = "Hi %s".formatted(UUID.randomUUID().toString());

		return ResponseEntity
            .ok()
            .body(message );
	}
   
}

package com.github.smsilva.wasp.demo.springsecuritydemo;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.websocket.server.PathParam;

@Controller
public class GreetingsController {

    @GetMapping("/greeting/{name}")
	public ResponseEntity<String> greeting(@PathParam("name") String name) {
		
		return ResponseEntity
            .ok()
            .body("Hi: " + name);
	}
   
}

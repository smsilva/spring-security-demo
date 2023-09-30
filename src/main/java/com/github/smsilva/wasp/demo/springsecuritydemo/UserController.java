package com.github.smsilva.wasp.demo.springsecuritydemo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
class UserController {

    @GetMapping("/me")
    String me() {
        return "John Doe";
    }

}

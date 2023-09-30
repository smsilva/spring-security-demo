package com.github.smsilva.wasp.demo.springsecuritydemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserController {

    @GetMapping("/me")
    String me() {
        return "John Doe";
    }

}

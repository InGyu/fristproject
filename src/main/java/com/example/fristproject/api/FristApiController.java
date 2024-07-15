package com.example.fristproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FristApiController {
    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World";
    }
}

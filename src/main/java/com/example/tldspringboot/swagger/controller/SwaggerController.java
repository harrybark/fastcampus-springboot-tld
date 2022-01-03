package com.example.tldspringboot.swagger.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/swagger-api")
public class SwaggerController {

    @GetMapping("/swaggers")
    public String hiSwagger() {
        return "Hi Swagger!";
    }
}

package com.example.client_project.client.controller;

import com.example.client_project.client.dto.Req;
import com.example.client_project.client.dto.UserResponse;
import com.example.client_project.client.service.RestTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client-api")
public class ClientController {

    private final RestTemplateService restTemplateService;

    public ClientController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/test")
    public UserResponse hello() {
        return restTemplateService.hello();
    }

    @GetMapping("/user-service")
    public Req<UserResponse> userService() {
        //return restTemplateService.exchange();
        return restTemplateService.genericExchange();
    }

}

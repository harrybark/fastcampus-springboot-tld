package com.example.tldspringboot.async.controller;

import com.example.tldspringboot.async.service.AsyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/async-api")
@RequiredArgsConstructor
public class AsyncApiController {

    private final AsyncService asyncService;

    @GetMapping("/hello")
    public CompletableFuture hello(){
        return asyncService.run();
    }
}

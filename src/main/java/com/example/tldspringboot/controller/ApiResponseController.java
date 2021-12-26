package com.example.tldspringboot.controller;

import com.example.tldspringboot.dto.UserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiResponseController {

    @GetMapping("/text")
     public String textReturn(@RequestParam String account) {
        return account;
     }

     @PostMapping("/json")
    public UserRequest jsonReturn(@RequestBody UserRequest user){
        return user;
    }

    @PutMapping("/put-res")
    public ResponseEntity<UserRequest> putResponse(@RequestBody UserRequest user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}

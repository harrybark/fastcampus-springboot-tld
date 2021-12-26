package com.example.tldspringboot.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeleteApiController {

    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable("userId") String userId, @RequestParam String account){
        System.out.println("Delete ID : " + userId);
        System.out.println("Delete account : " + account);
    }
}

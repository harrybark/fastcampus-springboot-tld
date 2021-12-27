package com.example.tldspringboot.controller;

import com.example.tldspringboot.annotation.Decode;
import com.example.tldspringboot.annotation.Timer;
import com.example.tldspringboot.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-aop-test")
public class AopApiController {

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name) {
        System.out.println("get method [ id : " + id + ", name : " + name + "]");

        return id + ", " + name;
    }

    @PostMapping("/post")
    public User post(@RequestBody User user) {
        System.out.println("post method / id : " + user);
        return user;
    }

    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {
        //db logic
        Thread.sleep(100*2);
    }

    @Decode
    @PutMapping("/put")
    public User put(@RequestBody User user) {
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println("post method / id : " + user);
        return user;
    }
}

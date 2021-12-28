package com.example.tldspringboot.controller;

import com.example.tldspringboot.dto.ExceptionUser;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("exception-api")
public class ExceptionController {

    @GetMapping("")
    public ExceptionUser get(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age){
        ExceptionUser user = new ExceptionUser();
        user.setName(name);
        user.setAge(age);

        int a = 10 + age;
        return user;
    }

    @PostMapping("")
    public ExceptionUser post(@Valid @RequestBody ExceptionUser user){

        return user;
    }
}

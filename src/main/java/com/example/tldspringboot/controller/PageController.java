package com.example.tldspringboot.controller;

import com.example.tldspringboot.dto.UserRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @GetMapping("/main")
    public String main() {
        return "main.html";
    }

    //ResponseEntity

    //ReponseBody
    @ResponseBody
    @GetMapping("/user")
    public UserRequest user() {
        var user = new UserRequest();
        user.setName("Harry");
        user.setAge(30);
        user.setPhoneNumber("010-1234-5678");
        user.setMail("mail@domain.com");

        return user;
    }
}


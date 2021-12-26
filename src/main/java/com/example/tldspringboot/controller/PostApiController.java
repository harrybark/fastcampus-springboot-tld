package com.example.tldspringboot.controller;

import com.example.tldspringboot.dto.PostRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostApiController {

    @PostMapping("/post")
    public void post(@RequestBody Map<String, Object> requestData) {
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        requestData.forEach((key, value) -> {
            System.out.println("key : " + key + " -> value : " + value);
        });
    }

    @PostMapping("/dto-post")
    public void dtoPost(@RequestBody PostRequestDto requestData) {
        System.out.println(new Object(){}.getClass().getEnclosingMethod().getName());
        System.out.println(requestData);
    }

}

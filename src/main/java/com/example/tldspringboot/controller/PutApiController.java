package com.example.tldspringboot.controller;

import com.example.tldspringboot.dto.PutRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PutApiController {

    @PutMapping(path = "/put")
    public PutRequestDto put(@RequestBody PutRequestDto request){
        System.out.println(request);
        // RestController는 ObjectMapper에서 그대로 리턴해줌.
        return request;
    }

    @PutMapping(path = "/put/{userId}")
    public PutRequestDto putPathVariable(@RequestBody PutRequestDto request, @PathVariable("userId") Long userId){
        System.out.println(request);
        System.out.println("userId : " + userId);
        // RestController는 ObjectMapper에서 그대로 리턴해줌.
        return request;
    }
}

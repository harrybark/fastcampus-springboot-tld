package com.example.tldspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @author Harry (@aka 갈색토마토)
 * @since 2021.12.23
 * @apiNote Rest API를 처리하는 Controller
 */
@RestController // 해당 Class는 REST API를 처리하는 컨트롤러
@RequestMapping("/api") // RequestMapping URL을 지정해주는 Annotation
public class ApiController {

    @GetMapping("/hello") // http://localhost:9090/api/hello
    public String hello() {
        return "hello spring boot!";
    }
}

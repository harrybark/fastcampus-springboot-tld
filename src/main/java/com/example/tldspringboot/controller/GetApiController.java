package com.example.tldspringboot.controller;

import com.example.tldspringboot.dto.UserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    @GetMapping(path = "/hello") // http://localhost:8080/api/get/hello
    public String getHello() {
        return "get Hello Spring Boot!";
    }

    // 구버전
    @RequestMapping(path = "/hello2", method = RequestMethod.GET) // get, post, put, delete
    public String oldRequestMapping() {
        return "get Hello Spring Boot!";
    }

    @GetMapping("/path-variable/{name}") // http://localhost:8080/api/get/path-variable/{name}
    public String pathVariable(@PathVariable(name = "name") String pathName) {

        System.out.println("Path Variable : " + pathName);
        return pathName;
    }

    // http://localhost:8080/api/get/query-param?user=devharry&email=harry@email.com&age=30
    @GetMapping(path ="/query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam) {
        StringBuffer sb = new StringBuffer();
        queryParam.entrySet().forEach(entry -> {
            sb.append(entry.getKey() + " : " + entry.getValue() + "\n");
        });
        return sb.toString();
    }

    // 변수가 많아지게 되면 모두 추가되어야 한다는 단점이 있다.
    @GetMapping(path ="/query-param02")
    public String queryParam02(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam int age) {

        return "name : " + name + ", email : " + email + " , age : " + age ;
    }

    @GetMapping(path ="/query-param03")
    public String queryParam03(UserRequest userRequest) {

        return userRequest.toString();
    }


}

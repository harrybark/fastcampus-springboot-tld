package com.example.server_project.server.controller;

import com.example.server_project.server.dto.Req;
import com.example.server_project.server.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Slf4j
@RestController
@RequestMapping("/server-api")
public class ServerController {

    @GetMapping("/naver")
    public String naver() {
        String query = "갈비집";
        String encode = Base64.getEncoder().encodeToString(query.getBytes(StandardCharsets.UTF_8));

        //https://openapi.naver.com
        // /v1/search/local.json?
        // query=%EC%A3%BC%EC%8B%9D
        // &display=10
        // &start=1
        // &sort=random
        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com")
                .path("/v1/search/local.json")
                .queryParam("query", query)
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "random")
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("x-Naver-Client-Id", "LuoaV_7wBwJm05XGrtmV")
                .header("x-Naver-Client-Secret", "SEXo60bZSU")
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        return result.getBody();
    }

    @GetMapping("/hello")
    public User serverHello(@RequestParam String name, @RequestParam int age){
        log.info("method : {}", new Object(){}.getClass().getEnclosingMethod().getName());
        User user = new User();
        user.setName(name);
        user.setAge(age);

        return user;
    }

    @PostMapping("/user-service/user/{userId}/name/{userName}")
    public Req<User> post(HttpEntity<String> entity,
                          @RequestBody Req<User> user,
                          @PathVariable("userId") int userId,
                          @PathVariable("userName") String userName,
                          @RequestHeader("x-authorization") String authorization) {
        log.info("method : {}", new Object(){}.getClass().getEnclosingMethod().getName());
        log.info("user : {}, userId : {}, userName : {}, authorization : {}", user, userId, userName, authorization);
        log.info("entity : {}", entity);

        Req<User> response = new Req<>();
        response.setHeader(new Req.Header());
        response.setBody(user.getBody());

        return response;
    }
}

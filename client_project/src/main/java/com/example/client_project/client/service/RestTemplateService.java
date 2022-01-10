package com.example.client_project.client.service;

import com.example.client_project.client.dto.Req;
import com.example.client_project.client.dto.UserRequest;
import com.example.client_project.client.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class RestTemplateService {

    // http://localhost:8080/api/server/hello
    // response
    public UserResponse hello() {

        URI uriComponentsBuilder = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/server-api/hello")
                .queryParam("name", "steve")
                .queryParam("age", 10)
                .encode()
                .build()
                .toUri();

        log.info("uri : {}", uriComponentsBuilder);

        RestTemplate restTemplate = new RestTemplate();
        //String result = restTemplate.getForObject(uriComponentsBuilder, String.class);
        //ResponseEntity<String> responseEntity = restTemplate.getForEntity(uriComponentsBuilder, String.class);
        ResponseEntity<UserResponse> responseEntity = restTemplate.getForEntity(uriComponentsBuilder, UserResponse.class);

        return responseEntity.getBody();
    }

    public UserResponse postService() {
        // http://localhost:9090/server-api/user-service/user/{userId}/name/{userName}
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/server-api/user-service/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand("100","steve")
                .toUri();

        log.info("uri : {}", uri);

        // http body -> object -> object mapper -> json -> test template -> http body json
        UserRequest user = new UserRequest();
        user.setName("harry park");
        user.setAge(20);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri, user, UserResponse.class);

        return response.getBody();
    }

    public UserResponse exchange() {
        // http://localhost:9090/server-api/user-service/user/{userId}/name/{userName}
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/server-api/user-service/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand("100","steve")
                .toUri();

        log.info("uri : {}", uri);

        // http body -> object -> object mapper -> json -> test template -> http body json
        UserRequest user = new UserRequest();
        user.setName("harry park");
        user.setAge(20);

        RequestEntity<UserRequest> requestRequestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .body(user);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<UserResponse> response = restTemplate.exchange(requestRequestEntity, UserResponse.class);

        return response.getBody();
    }

    public Req<UserResponse> genericExchange() {
        // http://localhost:9090/server-api/user-service/user/{userId}/name/{userName}
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/server-api/user-service/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand("100","steve")
                .toUri();

        log.info("uri : {}", uri);

        // http body -> object -> object mapper -> json -> test template -> http body json
        UserRequest user = new UserRequest();
        user.setName("harry park");
        user.setAge(20);

        Req<UserRequest> req = new Req<>();
        req.setHeader(
            new Req.Header()
        );

        req.setBody(
                user
        );

        req.getBody();

        RequestEntity<Req<UserRequest>> requestRequestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Req<UserResponse>>  response =
                restTemplate.exchange(requestRequestEntity, new ParameterizedTypeReference<Req<UserResponse>>(){});

        return response.getBody();
    }
}

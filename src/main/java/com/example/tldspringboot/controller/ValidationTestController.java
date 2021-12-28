package com.example.tldspringboot.controller;

import com.example.tldspringboot.dto.ValidationUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/valid-api")
public class ValidationTestController {

    @PostMapping("/user")
    public ResponseEntity user(@Valid @RequestBody ValidationUser user, BindingResult bindingResult) {
        System.out.println(user);
        if(bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String msg = objectError.getDefaultMessage();
                System.out.println("field : " + field.getField() + " // message : " + msg);

                sb.append("field : " + field.getField());
                sb.append("\t");
                sb.append("message : " + msg);

            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
        }


        return ResponseEntity.ok(user);
    }
}

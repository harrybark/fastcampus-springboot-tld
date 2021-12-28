package com.example.tldspringboot.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = {"com.example.*"})
public class GlobalControllerAdvice {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity exception(Exception e) {


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getClass());
    }

    //org.springframework.web.bind.MethodArgumentNotValidException
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}

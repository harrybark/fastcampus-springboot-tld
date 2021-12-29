package com.example.tldspringboot.advice;

import com.example.tldspringboot.controller.ExceptionController;
import com.example.tldspringboot.dto.Error;
import com.example.tldspringboot.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

//@RestControllerAdvice(basePackages = {"com.example.*"})
@RestControllerAdvice(basePackageClasses = {ExceptionController.class})
public class GlobalControllerAdvice {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity exception(Exception e) {
        System.out.println("Exception Caused : " + e.getClass().getName());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }

    //org.springframework.web.bind.MethodArgumentNotValidException
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {

        List<Error> errorList = new ArrayList<>();
        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getAllErrors().forEach(objectError -> {
            FieldError field = (FieldError) objectError;

            String fieldName = field.getField();
            String message = field.getDefaultMessage();
            String value = field.getRejectedValue().toString();

            Error error = new Error();
            error.setField(fieldName);
            error.setMessage(message);
            error.setInvalidValue(value);
            errorList.add(error);

        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(request.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity constraintViolationException(ConstraintViolationException e,  HttpServletRequest request){
        List<Error> errorList = new ArrayList<>();

        e.getConstraintViolations().forEach(errors -> {

            Stream<Path.Node> stream = StreamSupport.stream(errors.getPropertyPath().spliterator(), false);
            List<Path.Node> list = stream.collect(Collectors.toList());
            String field = list.get(list.size()-1).getName();
            String message = errors.getMessage();
            String invalidValue = errors.getInvalidValue().toString();

            Error error = new Error();
            error.setField(field);
            error.setMessage(message);
            error.setInvalidValue(invalidValue);
            errorList.add(error);
        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(request.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = {MissingServletRequestParameterException.class})
    public ResponseEntity missingServletRequestParameterException(MissingServletRequestParameterException e,  HttpServletRequest request) {

        String fieldName = e.getParameterName();
        String fieldMsg = e.getMessage();

        List<Error> errorList = new ArrayList<>();
        Error error = new Error();
        error.setField(fieldName);
        error.setMessage(fieldMsg);
        errorList.add(error);

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorList(errorList);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(request.getRequestURI());
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setResultCode("FAIL");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("");
    }
}

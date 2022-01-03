package com.example.tldspringboot.junit.calc.controller;

import com.example.tldspringboot.junit.calc.component.Calculator;
import com.example.tldspringboot.junit.calc.dto.CalcReq;
import com.example.tldspringboot.junit.calc.dto.CalcRes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calc-api")
@RequiredArgsConstructor
public class CalculatorApiController {

    private final Calculator calculator;

    @GetMapping("/sum")
    public int sum(@RequestParam int x, @RequestParam int y) {

        return calculator.sum(x, y);
    }

    @PostMapping("/json-sum")
    public CalcRes jsonSum(@RequestBody CalcReq req) {

        int result = calculator.sum(req.getX(), req.getY());
        CalcRes res = new CalcRes();
        res.setResult(result);
        res.setResponseBody(new CalcRes.Body());
        return res;
    }
}

package com.example.tldspringboot.junit.calc;

import com.example.tldspringboot.junit.calc.component.Calculator;
import com.example.tldspringboot.junit.calc.component.KrwCalculator;
import com.example.tldspringboot.junit.calc.component.MarkApi;
import com.example.tldspringboot.junit.calc.component.UsCalculator;

public class TestCalculator {

    public static void main(String[] args) {

        Calculator calculator = new Calculator(new KrwCalculator());

        System.out.println(calculator.sum(10, 10));

        MarkApi markApi = new MarkApi();
        UsCalculator usCalculator = new UsCalculator(markApi);
        usCalculator.init();
        Calculator calculator1 = new Calculator(usCalculator);
        System.out.println(calculator1.sum(10,20));
    }
}

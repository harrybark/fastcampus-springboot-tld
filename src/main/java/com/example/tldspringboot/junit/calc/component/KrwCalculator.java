package com.example.tldspringboot.junit.calc.component;

import org.springframework.stereotype.Component;

@Component("krwCalculator")
public class KrwCalculator implements ICalculator {

    private int price = 1;

    @Override
    public int sum(int x, int y) {
        x*= price;
        y *= price;
        return x + y;
    }

    @Override
    public int minus(int x, int y) {
        x*= price;
        y *= price;
        return x - y;
    }

    @Override
    public void init() {
        this.price = 1;
    }
}

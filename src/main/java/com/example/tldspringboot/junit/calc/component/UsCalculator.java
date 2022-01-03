package com.example.tldspringboot.junit.calc.component;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component("usCalculator")
@RequiredArgsConstructor
public class UsCalculator implements ICalculator {

    private int price = 1;

    private final MarkApi markApi;

    public void init () {
        // naver
        // kakao
        this.price *= markApi.connect();
    }

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


}

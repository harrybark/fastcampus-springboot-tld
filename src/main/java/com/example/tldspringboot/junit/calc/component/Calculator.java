package com.example.tldspringboot.junit.calc.component;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Calculator {

    private final ICalculator iCalculator;

    public Calculator(@Qualifier("usCalculator") ICalculator iCalculator) {
        this.iCalculator = iCalculator;
    }


    public int sum(int x, int y) {
        iCalculator.init();
        return this.iCalculator.sum(x, y);
    }

    public int minus(int x, int y) {
        iCalculator.init();
        return this.iCalculator.minus(x, y);
    }

}

package com.example.tldspringboot.calcTest;

import com.example.tldspringboot.junit.calc.component.Calculator;
import com.example.tldspringboot.junit.calc.component.MarkApi;
import com.example.tldspringboot.junit.calc.component.UsCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UsCalculatorTest {

    @Mock
    public MarkApi markApi;

    @BeforeEach
    public void init(){
        Mockito.lenient().when(markApi.connect()).thenReturn(3000);
    }

    @Test
    public void testHello() {
        System.out.println("hello JUnit");
    }

    @Test
    public void dollarTest() {

        MarkApi markApi = new MarkApi();
        UsCalculator usCalculator = new UsCalculator(markApi);
        usCalculator.init();
        Calculator calculator = new Calculator(usCalculator);

        Assertions.assertEquals(22000, calculator.sum(10, 10));
    }

    @Test
    public void dollarMockTest() {

        UsCalculator usCalculator = new UsCalculator(this.markApi);
        usCalculator.init();
        Calculator calculator = new Calculator(usCalculator);

        Assertions.assertEquals(22000, calculator.sum(10, 10));

    }
}

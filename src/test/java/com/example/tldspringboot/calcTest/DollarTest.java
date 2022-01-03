package com.example.tldspringboot.calcTest;

import com.example.tldspringboot.junit.calc.component.Calculator;
import com.example.tldspringboot.junit.calc.component.MarkApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class DollarTest {

    @MockBean
    private MarkApi markApi;

    @Autowired
    private Calculator Calculator;

    @Test
    public void dollarTest(){

        Mockito.when(markApi.connect()).thenReturn(3000);

        int sum = Calculator.sum(10, 10);
        Assertions.assertEquals(60000, sum);

    }
}

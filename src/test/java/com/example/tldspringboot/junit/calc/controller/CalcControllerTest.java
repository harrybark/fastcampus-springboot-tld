package com.example.tldspringboot.junit.calc.controller;

import com.example.tldspringboot.junit.calc.component.Calculator;
import com.example.tldspringboot.junit.calc.component.MarkApi;
import com.example.tldspringboot.junit.calc.component.UsCalculator;
import com.example.tldspringboot.junit.calc.dto.CalcReq;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CalculatorApiController.class)
@AutoConfigureWebMvc
@Import({Calculator.class,UsCalculator.class})
public class CalcControllerTest {

    @MockBean
    private MarkApi markApi;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void init(){
        Mockito.when(markApi.connect()).thenReturn(3000);
    }

    @Test
    public void sumTest() throws Exception {
        // http://localhost:8080/calc-api/sum
        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/calc-api/sum")
                        .queryParam("x", "10")
                        .queryParam("y", "10")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().string("60000")
        ).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void jsonSumTest() throws Exception {

        CalcReq req = new CalcReq();
        req.setX(10);
        req.setY(10);

        String json = new ObjectMapper().writeValueAsString(req);
        mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:8080/calc-api/json-sum")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.result").value("60000")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("$.responseBody.resultCode").value("OK")
        ).andDo(
                MockMvcResultHandlers.print()
        );
    }

}

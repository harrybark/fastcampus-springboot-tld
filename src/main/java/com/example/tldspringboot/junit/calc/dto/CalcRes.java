package com.example.tldspringboot.junit.calc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalcRes {

    private int result;

    private Body responseBody ;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Body {
        private String resultCode = "OK";
    }

}

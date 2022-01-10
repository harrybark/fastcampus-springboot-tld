package com.example.server_project.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Req<T> {

    private Header header;

    private T body;

    @Data
    public static class Header {
        private String responseCode;
    }
}

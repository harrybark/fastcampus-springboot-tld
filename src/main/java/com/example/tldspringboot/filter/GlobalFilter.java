package com.example.tldspringboot.filter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/filter-test-api/user")
public class GlobalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // before processing
        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper resp = new ContentCachingResponseWrapper((HttpServletResponse) response);

        chain.doFilter(req, resp);

        String url = req.getRequestURI();
        // after processing
        String reqContent = new String(req.getContentAsByteArray());
        log.info("request url : {}, request body : {}", url, reqContent);

        String resContent = new String(resp.getContentAsByteArray());
        int httpStatusCode = resp.getStatus();
        log.info("response status : {}, responseBody : {}", httpStatusCode, resContent);

        // body를 copy해주는 메서드
        resp.copyBodyToResponse();

        /*
        BufferedReader br = req.getReader();
        br.lines().forEach(line -> {
            log.info("url : {}, line : {} ", url, line);
        });
        */
    }
}

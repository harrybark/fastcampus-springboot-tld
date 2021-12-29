package com.example.tldspringboot.interceptor.interceptor;

import com.example.tldspringboot.interceptor.annotation.Auth;
import com.example.tldspringboot.interceptor.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Slf4j
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();

        URI uri = UriComponentsBuilder.fromUriString(request.getRequestURI()).query(request.getQueryString()).build().toUri();

        log.info("request url : {} ", url);

        boolean hasAnnotation = checkAnnotation(handler, Auth.class);

        if (hasAnnotation) {
            // check auth
            String query = uri.getQuery();
            if(query.equals("name=harry")){
                return true;
            }

            throw new AuthException();
        }

        return false;
    }

    public boolean checkAnnotation(Object handler, Class clazz){
        // resource javascript, html - pass
        if(handler instanceof ResourceHttpRequestHandler) {
            return true;
        }

        // annotation check
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        if(null != handlerMethod.getMethodAnnotation(clazz) || null != handlerMethod.getBeanType().getAnnotation(clazz)){
            // has Auth annotation - pass
            return true;
        }

        return false;
    }
}

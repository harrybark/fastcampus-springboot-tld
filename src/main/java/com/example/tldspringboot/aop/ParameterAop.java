package com.example.tldspringboot.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class ParameterAop {

    @Pointcut("execution(* com.example.tldspringboot.controller..*.*(..))")
    private void pointCut() {

    }

    @Before("pointCut()")
    public void beforeAction(JoinPoint joinPoint){
        System.out.println("Before Action");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println(method.getName());

        Arrays.stream(joinPoint.getArgs()).forEach(s -> {
            System.out.println(s.getClass().getSimpleName());
            System.out.println(s);
        });
    }

    @AfterReturning(value = "pointCut()", returning = "obj")
    public void afterAction(JoinPoint joinPoint, Object obj){
        System.out.println("After Action");
        System.out.println(obj);
    }

}

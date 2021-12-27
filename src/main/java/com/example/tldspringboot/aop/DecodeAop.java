package com.example.tldspringboot.aop;

import com.example.tldspringboot.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Base64;

@Aspect
@Component
public class DecodeAop {


    @Pointcut("execution(* com.example.tldspringboot.controller..*.*(..))")
    private void pointCut() {

    }

    @Pointcut("@annotation(com.example.tldspringboot.annotation.Decode)")
    private void enableDecode() {

    }

    @Before("pointCut() && enableDecode()")
    public void beforeDecode(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        System.out.println("Method Name : " + method.getName());

        Arrays.stream(joinPoint.getArgs()).forEach(s -> {
            if(s instanceof User) {

                User user = User.class.cast(s);
                String base64Name = user.getUser();

                String newName = null;
                try {
                    newName = new String(Base64.getDecoder().decode(base64Name), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                user.setUser(newName);

                System.out.println(user);
            }
        });
    }

    @AfterReturning(value = "pointCut() && enableDecode()", returning = "returnObj")
    public void afterDecode(JoinPoint joinPoint, Object returnObj) {

        if(returnObj instanceof  User) {

            User user = User.class.cast(returnObj);
            String email = user.getUser();
            String base64Name = Base64.getEncoder().encodeToString(user.getUser().getBytes());

            user.setUser(base64Name);
        }
    }

}

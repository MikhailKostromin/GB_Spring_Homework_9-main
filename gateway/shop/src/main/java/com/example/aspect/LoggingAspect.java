package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.services.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("_____Executing: " + joinPoint.getSignature().getName() + "_______");
    }

}


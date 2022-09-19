package com.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PrefAspect {

    @Around("execution(* com.spring..*(..))")
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {

        long l = System.currentTimeMillis();
        Object retVal = pjp.proceed();

        System.out.println(System.currentTimeMillis() - l);

        return retVal;
    }

    @Before("execution(* com.spring..*(..))")
    public void beforeCheck(){
        System.out.println("before");
    }
}

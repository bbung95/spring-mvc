//package com.spring.review;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class TestAop {
//
//    @Before("execution(* com.spring.review..*(..))")
//    public void beforeAop(){
//        System.out.println("==== before AOP ====");
//    }
//
////    @Around("execution(* com.spring.review.TestService..*(..))")
//    @Around("execution(* com.spring.review..*(..))")
//    public void aroundAop(ProceedingJoinPoint point) throws Throwable {
//        System.out.println("==== around before AOP ====");
//
//        Object proceed = point.proceed();
//        if(proceed != null){
//            System.out.println(proceed.getClass());
//        }
//
//        System.out.println("==== around after AOP ====");
//    }
//
//    @After("execution(* com.spring.review..*(..))")
//    public void afterAop(){
//        System.out.println("==== after AOP ====");
//    }
//
////    @Around("execution(public String hello (..))")
////    public void aroundAop(){
////        System.out.println("==== around AOP ====");
////    }
//
//}

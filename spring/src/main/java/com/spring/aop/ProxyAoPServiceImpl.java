//package com.spring.aop;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Service;
//
//@Primary
//@Service
//public class ProxyAoPServiceImpl implements AoPService{
//
//    @Autowired
//    private AoPServiceImpl aoPServiceImpl;
//
//    @Override
//    public void createEvent() {
//        long begin = System.currentTimeMillis();
//        aoPServiceImpl.createEvent();
//        System.out.println(System.currentTimeMillis() - begin);
//    }
//
//    @Override
//    public void publishEvent() {
//        long begin = System.currentTimeMillis();
//        aoPServiceImpl.publishEvent();
//        System.out.println(System.currentTimeMillis() - begin);
//    }
//
//    @Override
//    public void deleteEvent() {
//        aoPServiceImpl.deleteEvent();
//    }
//}

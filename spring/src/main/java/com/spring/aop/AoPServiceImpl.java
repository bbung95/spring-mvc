package com.spring.aop;

import org.springframework.stereotype.Service;

@Service
public class AoPServiceImpl implements AoPService{

    @Override
    public void createEvent() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Create an Event");
    }

    @Override
    public void publishEvent() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Published an Event");
    }

    @Override
    public void deleteEvent(){
        System.out.println("Delete an Event");
    }
}

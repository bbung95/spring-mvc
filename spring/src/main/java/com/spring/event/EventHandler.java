package com.spring.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

//@Component
public class EventHandler {

    @EventListener
    public void handler(MyEvent event){
        System.out.println("Event = " + event.getObject());
        System.out.println("Event = " + event.getData());
    }
}

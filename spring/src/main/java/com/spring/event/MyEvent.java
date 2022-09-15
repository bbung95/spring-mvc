package com.spring.event;

public class MyEvent {

    private Object object;

    private int data;

    public MyEvent(Object object, int data) {
        this.object = object;
        this.data = data;
    }

    public Object getObject() {
        return object;
    }

    public int getData() {
        return data;
    }
}

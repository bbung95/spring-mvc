package com.spring.spel;

import org.springframework.stereotype.Component;

//@Component
public class SpELData {

    private String data = "Bean Data";

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

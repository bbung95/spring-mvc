package com.spring.validation;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Event {

    @NotNull
    private Integer id;

    @NotEmpty
    private String title;

    public Event() {
    }

    public Event(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}

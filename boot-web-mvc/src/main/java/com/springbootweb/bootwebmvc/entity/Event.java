package com.springbootweb.bootwebmvc.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.springbootweb.bootwebmvc.view.EventJsonView;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Event {

    private Integer id;

    @JsonView(EventJsonView.EventView.class)
    @NotBlank(message = "name의 값을 입력해주세요.")
    private String name;

    @JsonView(EventJsonView.EventView.class)
    @Min(value = 1, message = "1보다 큰 값을 입력해주세요")
    private Integer limit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}

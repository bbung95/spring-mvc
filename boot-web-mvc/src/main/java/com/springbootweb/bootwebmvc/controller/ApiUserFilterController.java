package com.springbootweb.bootwebmvc.controller;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springbootweb.bootwebmvc.dto.UserFilterResponseDto;
import com.springbootweb.bootwebmvc.dto.UserResponseDto;
import com.springbootweb.bootwebmvc.service.UserService;
import com.springbootweb.bootwebmvc.util.JsonFilterUtil;
import com.springbootweb.bootwebmvc.view.UserJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user-filter")
public class ApiUserFilterController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public MappingJacksonValue getUserDetail(@PathVariable String id, @RequestParam String field){
        UserFilterResponseDto user = userService.findUserFilter(id);
        return JsonFilterUtil.filterJsonData(user, field, "UserFilter");
    }

    @GetMapping("{id}/admin")
    public MappingJacksonValue getUserAdminDetail(@PathVariable String id, @RequestParam String field){
        UserFilterResponseDto user = userService.findUserFilter(id);
        return JsonFilterUtil.filterJsonData(user, field, "UserFilter");
    }

    @GetMapping
    public MappingJacksonValue getUserList(@RequestParam String field){
        List<UserFilterResponseDto> list = userService.findUserFilterList();

        PropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept(field.split(","));

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(list);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("{id}/modified")
    public MappingJacksonValue getUserModified(@PathVariable String id, @RequestParam String field){
        UserFilterResponseDto user = userService.findUserFilter(id);
        return JsonFilterUtil.filterJsonData(user, field, "UserFilter");
    }

}

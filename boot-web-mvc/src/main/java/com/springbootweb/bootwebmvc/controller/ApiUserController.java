package com.springbootweb.bootwebmvc.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootweb.bootwebmvc.dto.UserResponseDto;
import com.springbootweb.bootwebmvc.service.UserService;
import com.springbootweb.bootwebmvc.view.UserJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class ApiUserController {

    @Autowired
    private UserService userService;

//    @GetMapping("{id}")
//    @JsonView(UserJsonView.UserView.class)
//    public UserResponseDto getUserDetail(@PathVariable String id){
//
//        UserResponseDto user = userService.findUser(id);
//
//        System.out.println("user = " + user);
//
//        return user;
//    }

    @GetMapping("{id}")
    public Object getUserDetail(@PathVariable String id) throws JsonProcessingException {

        UserResponseDto user = userService.findUser(id);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = objectMapper.writerWithView(UserJsonView.UserView.class).writeValueAsString(user);

        return objectMapper.readValue(jsonData, Object.class);
    }

    @GetMapping("{id}/admin")
    @JsonView(UserJsonView.UserAdminView.class)
    public UserResponseDto getUserAdminDetail(@PathVariable String id){
        return userService.findUser(id);
    }

    @GetMapping
    @JsonView(UserJsonView.UserListView.class)
    public List<UserResponseDto> getUSerList(){
        return userService.findUserList();
    }

    @GetMapping("{id}/modified")
    @JsonView(UserJsonView.UserModifiedView.class)
    public UserResponseDto getUserModified(@PathVariable String id){
        return userService.findUser(id);
    }
}

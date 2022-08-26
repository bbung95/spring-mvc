package com.springbootweb.bootwebmvc.service;

import com.springbootweb.bootwebmvc.dto.UserResponseDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public UserResponseDto findUser(String id){

        UserResponseDto userResponse = getUserResponse(id);

        return userResponse;
    }

    public List<UserResponseDto> findUserList(){

        List<UserResponseDto> list = new ArrayList<>();

        list.add(getUserResponse("khy123123"));
        list.add(getUserResponse("admin123"));
        list.add(getUserResponse("rai"));
        list.add(getUserResponse("aslan"));

        return list;
    }

    private UserResponseDto getUserResponse(String userId){

        UserResponseDto user = new UserResponseDto();
        user.setUserId(userId);
        user.setPassword("qwe123123!");
        user.setNickname(userId);
        user.setUsername("UserName");
        user.setEmail("user@naver.com");
        user.setPhone("010-0000-1111");
        user.setCreateDate(LocalDateTime.now());
        user.setModifiedDate(LocalDateTime.now());

        return user;
    }
}

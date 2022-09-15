package com.springbootweb.bootwebmvc.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiUserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void getUserDetailTest() throws Exception{

        mockMvc.perform(get("/api/user/rai"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getUserAdminDetailTest() throws Exception{
        mockMvc.perform(get("/api/user/rai/admin"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getUserListTest() throws Exception{
        mockMvc.perform(get("/api/user"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getUserModifiedTest() throws Exception{
        mockMvc.perform(get("/api/user/rai/modified"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
package com.springbootweb.bootweb.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(controllers = SampleController.class)
@AutoConfigureMockMvc
@SpringBootTest
class SampleControllerTest {

    /**
     * Test MockMvc
     */
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void hello() throws Exception {

        mockMvc.perform(get("/hello").param("name", "bbung"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello bbung"));
    }

    @Test
    public void helloStatic() throws Exception{
        mockMvc.perform(get("/mobile/index.html"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.containsString("hello index")))
                .andExpect(header().exists(HttpHeaders.CACHE_CONTROL));
    }

    @Test
    public void stringMessage() throws Exception{
        mockMvc.perform(get("/message")
                .content("hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));
    }

}
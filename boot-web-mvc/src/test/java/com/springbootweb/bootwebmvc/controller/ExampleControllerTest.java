package com.springbootweb.bootwebmvc.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class ExampleControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void getEvents() throws Exception {

        mockMvc.perform(get("/events"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("GET /events"));
    }

    @Test
    public void getEventsId() throws Exception {

        int id = 1;

        mockMvc.perform(get("/events/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("GET /events/" + id));
    }

    @Test
    public void postEvents() throws Exception{

        mockMvc.perform(post("/events")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("POST /events"));
    }

    @Test
    public void deleteEvents() throws Exception{

        int id = 1;

        mockMvc.perform(delete("/events/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("DELETE /events/" + id));
    }

    @Test
    public void putEvents() throws Exception{

        int id = 1;

        mockMvc.perform(put("/events/" + id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("PUT /events/" +id));
    }
}
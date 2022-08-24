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
    public void getExample() throws Exception {

        mockMvc.perform(get("/example"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("GET /example"));
    }

    @Test
    public void getExampleId() throws Exception {

        int id = 1;

        mockMvc.perform(get("/example/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("GET /example/" + id));
    }

    @Test
    public void postExample() throws Exception{

        mockMvc.perform(post("/example")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("POST /example"));
    }

    @Test
    public void deleteExample() throws Exception{

        int id = 1;

        mockMvc.perform(delete("/example/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("DELETE /example/" + id));
    }

    @Test
    public void putExample() throws Exception{

        int id = 1;

        mockMvc.perform(put("/example/" + id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("PUT /example/" +id));
    }
}
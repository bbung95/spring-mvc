package com.springbootweb.bootweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbootweb.bootweb.entity.Person;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.oxm.Marshaller;
import org.springframework.test.web.servlet.MockMvc;

import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import java.io.StringWriter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(controllers = SampleController.class)
@AutoConfigureMockMvc
@SpringBootTest
class SampleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Marshaller marshaller;

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

    @Test
    public void jsonMessage() throws Exception{

        Person person = new Person();
        person.setId(2022);
        person.setName("bbung");

        String jsonString = objectMapper.writeValueAsString(person);

        mockMvc.perform(get("/json-message")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2022))
                .andExpect(jsonPath("$.name").value("bbung"));
    }

    @Test
    public void xmlMessage() throws Exception{

        Person person = new Person();
        person.setId(2022);
        person.setName("bbung");

        StringWriter stringWriter = new StringWriter();
        Result result = new StreamResult(stringWriter);
        marshaller.marshal(person, result);
        String xmlString = stringWriter.toString();

        mockMvc.perform(get("/json-message")
                        .contentType(MediaType.APPLICATION_XML)
                        .accept(MediaType.APPLICATION_XML)
                        .content(xmlString))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("person/id").string("2022"))
                .andExpect(xpath("person/name").string("bbung"));
    }
}
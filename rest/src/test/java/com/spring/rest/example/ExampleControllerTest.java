package com.spring.rest.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.rest.account.enums.AccountRole;
import com.spring.rest.account.service.AccountService;
import com.spring.rest.common.BaseControllerTest;
import com.spring.rest.domain.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ExampleControllerTest extends BaseControllerTest {

    @Autowired
    private AccountService accountService;

    private String createToken() throws Exception {

        ResultActions perform = mockMvc.perform(post("/api/authenticate")
                .content(objectMapper.writeValueAsString(getAccount())))
                .andDo(print());

        return perform.andReturn().getResponse().getContentAsString();
    }

    private Account getAccount(){
        Set<AccountRole> accountRoles = new HashSet<>();
        accountRoles.add(AccountRole.ADMIN);
        accountRoles.add(AccountRole.USER);

        String username = "bbung@gmail.com";
        String password = "1234";

        Account build = Account.builder()
                .email(username)
                .password(password)
                .roles(accountRoles)
                .build();

        Account account = accountService.saveAccount(build);

        return account;
    }

    @Test
    @DisplayName("DTO 응답요청")
    public void objectResponseTest() throws Exception {

        mockMvc.perform(get("/api/example/object"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("ResponseEntity 응답요청")
    public void responseEntityTest() throws Exception {

        mockMvc.perform(get("/api/example/response-entity"))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
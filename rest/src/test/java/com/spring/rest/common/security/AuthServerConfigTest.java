package com.spring.rest.common.security;

import com.spring.rest.account.enums.AccountRole;
import com.spring.rest.account.service.AccountService;
import com.spring.rest.common.BaseControllerTest;
import com.spring.rest.domain.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthServerConfigTest extends BaseControllerTest {

    @Autowired
    private AccountService accountService;

    @Test
    @DisplayName("인증 토큰을 발급 받는 테스트")
    public void getAuthToken() throws Exception {

        Set<AccountRole> accountRoles = new HashSet<>();
        accountRoles.add(AccountRole.ADMIN);
        accountRoles.add(AccountRole.USER);

        String username = "bbung@gmail.com";
        String password = "bbung";

        Account account = Account.builder()
                .email(username)
                .password(password)
                .roles(accountRoles)
                .build();

        accountService.saveAccount(account);

        String clientId = "myApp";
        String clientSecret = "pass";

        mockMvc.perform(post("/oauth/token")
                        .with(httpBasic(clientId, clientSecret))
                        .param("username", username)
                        .param("password", password)
                        .param("grant_type", "password"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("access_token").exists())
            ;

    }

}
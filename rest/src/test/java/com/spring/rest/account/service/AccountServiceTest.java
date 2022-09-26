package com.spring.rest.account.service;

import com.spring.rest.account.enums.AccountRole;
import com.spring.rest.account.repository.AccountRepository;
import com.spring.rest.common.BaseControllerTest;
import com.spring.rest.domain.Account;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AccountServiceTest {

    // Exception을 예측하여 테스트
    @Rule
    ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("로그인시 유저 아이디 조회 및 패스워드 확인")
    public void loginAccountTest() throws Exception {

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

        UserDetails userDetails = accountService.loadUserByUsername("bbung@gmail.com");

        assertThat(userDetails.getUsername()).isEqualTo("bbung@gmail.com");
        assertThat(passwordEncoder.matches(password, userDetails.getPassword())).isTrue();

    }

    @Test
    @DisplayName("계정 조회시 아이디가 존재하지 않을때 UsernameNotFoundException")
    public void findByUsernameFail() throws Exception {

        String username = "rai@gmail.com";

        UsernameNotFoundException usernameNotFoundException = assertThrows(UsernameNotFoundException.class,
                () -> accountService.loadUserByUsername(username));

        System.out.println(usernameNotFoundException.getMessage());

    }

}
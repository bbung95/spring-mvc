package com.spring.rest;

import com.spring.rest.account.enums.AccountRole;
import com.spring.rest.account.service.AccountService;
import com.spring.rest.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AccountRunner implements ApplicationRunner {

    private final AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Set<AccountRole> roleSet = new HashSet<AccountRole>();
        roleSet.add(AccountRole.ADMIN);

        Account account = Account.builder()
                .email("bbung@gmail.com")
                .password("1234")
                .roles(roleSet)
                .build();

        accountService.saveAccount(account);
    }
}

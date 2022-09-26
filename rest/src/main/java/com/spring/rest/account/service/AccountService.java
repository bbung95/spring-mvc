package com.spring.rest.account.service;

import com.spring.rest.account.enums.AccountRole;
import com.spring.rest.account.repository.AccountRepository;
import com.spring.rest.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new User(account.getEmail(), account.getPassword(), authorities(account.getRoles()));
    }

    public Account saveAccount(Account account){

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        Account result = accountRepository.save(account);

        return result;
    }

    private Collection<? extends GrantedAuthority> authorities(Set<AccountRole> accountRoleSet){

        return accountRoleSet.stream().map(item ->
            new SimpleGrantedAuthority("ROLE_" + item.name())
        ).collect(Collectors.toSet());
    }

}

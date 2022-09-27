package com.spring.rest.account;

import com.spring.rest.account.service.AccountService;
import com.spring.rest.domain.Account;
import com.spring.rest.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authenticate")
public class AccountController {

    private final JwtTokenUtil jwtTokenUtil;

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity tokenLogin(@RequestBody Account account){

        Account findAccount = accountService.userEmailAndPasswordCheck(account.getEmail(), account.getPassword());
        String token = jwtTokenUtil.successfulAuthentication(findAccount);
        User user = new User(findAccount.getEmail(), findAccount.getPassword(), findAccount.getAuthorities());
        new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));

        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}

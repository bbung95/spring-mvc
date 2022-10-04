package com.spring.rest.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.spring.rest.domain.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("{jwt.subject}")
    private String subject;

    @Value("{jwt.key}")
    private String key;

    public String successfulAuthentication(Account account){

        // RSA방식이 아닌 Hash암호방식
        String jwtToken = JWT.create()
                .withSubject(subject)
                .withExpiresAt(new Date(System.currentTimeMillis()+(10 * 6 * 60000)))
                .withClaim("id", account.getId())
                .withClaim("username", account.getEmail())
                .sign(Algorithm.HMAC512(key));

        return "Bearer "+jwtToken;
    }
}

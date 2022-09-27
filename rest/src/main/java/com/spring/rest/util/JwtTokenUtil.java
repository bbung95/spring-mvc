package com.spring.rest.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.spring.rest.domain.Account;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    // attempAuthentication실행 후 인증이 정상적으로 되었으면 successfulAuthentication 함수가 실행된다.
    // JWT 토큰을 만들어서 request요청한 사용자에게 JWT토큰을 respone해주면 된다.
    public String successfulAuthentication(Account account){

        System.out.println("successfulAuthentication 실행됨 : 인증이 완료되었다는 뜻");

        // RSA방식이 아닌 Hash암호방식
        String jwtToken = JWT.create()
                .withSubject("bbung")
                .withExpiresAt(new Date(System.currentTimeMillis()+(10 * 6 * 60000)))
                .withClaim("id", account.getId())
                .withClaim("username", account.getEmail())
                .sign(Algorithm.HMAC512("pass"));

        return "Bearer "+jwtToken;
    }
}

package com.spring.rest.common.security.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.spring.rest.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final AccountService accountService;

    private static final List<String> EXCLUDE_URL = Collections.unmodifiableList(
                                                Arrays.asList("/api/authenticate", "/docs/index.html"));

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("인증이나 권한이 필요한 주소 요청이 됨.");

        String jwtHeader = request.getHeader("Authorization");
        System.out.println("jwtHeader = " + jwtHeader);

        String token = null;
        String username = null;

        // header가 있는지 확인
        if(jwtHeader != null && jwtHeader.startsWith("Bearer ")){

            token = jwtHeader.replace("Bearer ", "").trim();

            // 유효시간 검증


            ///////////

            username = JWT.require(Algorithm.HMAC512("pass")).build().verify(token).getClaim("username").asString();
            System.out.println("username = " + username);
        }

        // JWT 토큰을 검증을 해서 정상적인 사용자인지 확인

        if(username != null && SecurityContextHolder.getContext().getAuthentication() != null){

            System.out.println("username 정상");
            UserDetails userDetails = accountService.loadUserByUsername(username);

            // JWT 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // 강제로 시큐리티의 세션에 접근하여 Authentication 객체를 저장.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // Filter 제외 PATH 추가

        System.out.println("Filter 제외 URL");
        System.out.println(request.getServletPath());
        return EXCLUDE_URL.stream().anyMatch(item -> item.equalsIgnoreCase(request.getServletPath()));
    }
}

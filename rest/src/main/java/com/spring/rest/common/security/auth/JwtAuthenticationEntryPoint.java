package com.spring.rest.common.security.auth;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        String message = null;
        int statusCode = 400;

        if(authException instanceof UsernameNotFoundException){
            message = authException.getMessage();
        }else{
            message = "UnAuthorize";
            statusCode = 401;
        }

        response.sendError(statusCode, message);
    }
}

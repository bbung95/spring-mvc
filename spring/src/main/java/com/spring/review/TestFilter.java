package com.spring.review;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import java.io.IOException;

@Component
public class TestFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("==== Filter Init ====");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("==== Filter Start ====");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

        System.out.println("==== Filter destroy ====");
    }
}

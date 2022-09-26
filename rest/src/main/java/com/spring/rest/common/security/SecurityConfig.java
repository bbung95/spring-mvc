package com.spring.rest.common.security;

import com.spring.rest.account.service.AccountService;
import com.spring.rest.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public TokenStore tokenStore(){
        return new InMemoryTokenStore();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // security 적용되지 않도록 설정
        // rest docs path 설정
//        web.ignoring().mvcMatchers("/docs/index.html");
        web.ignoring().mvcMatchers("/docs/index.html");
        // static path 설정
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .anonymous()
//            .and()
//                .formLogin()
//            .and()
//                .authorizeRequests()
//                .mvcMatchers(HttpMethod.GET, "/api/**").anonymous()
//                .anyRequest().authenticated();
//    }
}

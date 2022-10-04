package com.spring.rest.index.controller;

import com.spring.rest.account.service.AccountService;
import com.spring.rest.domain.Account;
import com.spring.rest.events.controller.EventController;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Base64;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class IndexController {

    private final AccountService accountService;

    @GetMapping
    public RepresentationModel index(){

        RepresentationModel model = new RepresentationModel<>();
        model.add(linkTo(EventController.class).withRel("events"));

        return model;
    }

    @GetMapping("basic-auth")
    public ResponseEntity httpBasic(HttpServletRequest request, HttpServletResponse response){

        String authorization = request.getHeader("Authorization");
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        boolean authorizationCheck = false;

        if(authorization.startsWith("Basic")) {

            Account account = parserBasicAuth(authorization);
            if(account != null){
                authorizationCheck = true;
                status = HttpStatus.OK;
            }
        }

        return ResponseEntity.status(status).body(authorizationCheck);
    }

    private Account parserBasicAuth(String authorization){

        // id:pwd 추출
        String replaceAuth = authorization.replace("Basic ", "");
        String decodedString = null;

        // base64 decoder
        try{
            byte[] decodedBytes = Base64.getDecoder().decode(replaceAuth);
            decodedString = new String(decodedBytes);
        }catch (IllegalArgumentException error){
            return null;
        }

        String[] split = decodedString.split(":");
        if(split.length != 2){
            return null;
        }

        Account account = accountService.userEmailAndPasswordCheck(split[0], split[1]);

        return account;
    }

}

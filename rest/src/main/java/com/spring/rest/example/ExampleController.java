package com.spring.rest.example;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/example")
public class ExampleController {

    @Getter
    @Setter
    @Builder
    public static class HttpResponse{

        private HttpStatus statusCode;
        private HttpHeaders headers;
        private Object body;
    }

    @GetMapping("object")
    public HttpResponse objectResponse(){

        HttpResponse build = HttpResponse.builder()
                .statusCode(HttpStatus.OK)
                .body("Object test")
                .build();

        return build;
    }

    @GetMapping("response-entity")
    public ResponseEntity responseEntity(){

        return ResponseEntity.status(HttpStatus.OK).body("Object test");
    }
}

package com.nhnent.edu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class Example {
    public static void main(String[] args) {
        SpringApplication.run(Example.class);
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

}

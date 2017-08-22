package com.nhnent.edu.springboot.children;

import com.nhnent.edu.springboot.children.service.HelloSpeaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FirstChildConfig {
    @Autowired
    private HelloSpeaker helloSpeaker;

    @Bean
    public HelloSpeaker helloSpeaker() {
        return new HelloSpeaker();
    }

    @RequestMapping("/hello")
    public String hello() {
        return helloSpeaker.speak();
    }
}

package com.nhnent.edu.springboot.children;

import com.nhnent.edu.springboot.children.service.HelloSpeaker;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SecondChildConfig {
    @Bean
    public HelloSpeaker helloSpeaker() {
        return new HelloSpeaker();
    }
}

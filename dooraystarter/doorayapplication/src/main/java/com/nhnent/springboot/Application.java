package com.nhnent.springboot;

import com.nhnent.springboot.dooray.DoorayHook;
import com.nhnent.springboot.dooray.DoorayHookSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {
    @Autowired
    DoorayHookSender doorayHookSender;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationListener() {
        return new ApplicationListener<ApplicationReadyEvent>() {
            @Override
            public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
                DoorayHook doorayHook = new DoorayHook();
                doorayHook.setBotName("test");
                doorayHook.setText("test!!");
                doorayHookSender.send(doorayHook);
            }
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

package com.nhnent.edu.springboot.devtoolsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
@SpringBootApplication
public class DevtoolsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevtoolsDemoApplication.class, args);
    }

    @GetMapping("/")
    public String hello(Map<String, Object> model) {
        model.put("name", "정명주!!@@@");
        return "index";
    }
}

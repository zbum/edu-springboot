package com.nhnent.edu.springboot.devtoolsdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class DevtoolsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevtoolsDemoApplication.class, args);
    }

    @GetMapping("/")
    public String hello(ModelMap model) {
        model.put("name", "정지범");
        return "index";
    }
}

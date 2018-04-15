package com.nhnent.edu.springboot.autoconfig.properties.propertiesdemo;

import com.nhnent.edu.springboot.autoconfig.properties.propertiesdemo.config.EduProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableConfigurationProperties(EduProperties.class)
@SpringBootApplication
@RestController
public class PropertiesDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropertiesDemoApplication.class, args);
    }

    @Autowired
    private EduProperties eduProperties;

    @GetMapping("/student/count/max")
    public Long studentMaxCount(){
        return eduProperties.getStudentMaxCount();
    }
}

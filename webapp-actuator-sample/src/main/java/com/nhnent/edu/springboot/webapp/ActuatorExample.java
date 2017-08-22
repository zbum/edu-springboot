package com.nhnent.edu.springboot.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ActuatorExample {

    private final static Logger logger = LoggerFactory.getLogger(ActuatorExample.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ActuatorExample.class, args);
    }

    @RequestMapping("/")
    public String home() {
        logger.info("++++++++++++++++test++++++++++++++++");
        return "Hello World!";
    }

}
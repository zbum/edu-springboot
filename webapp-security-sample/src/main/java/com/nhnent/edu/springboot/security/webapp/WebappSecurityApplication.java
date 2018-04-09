package com.nhnent.edu.springboot.security.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class WebappSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebappSecurityApplication.class, args);
	}

	@GetMapping("/")
    public String hello() {
	    return "hello";
    }

}

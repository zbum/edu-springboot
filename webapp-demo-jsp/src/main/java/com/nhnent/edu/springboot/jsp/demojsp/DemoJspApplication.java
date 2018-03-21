package com.nhnent.edu.springboot.jsp.demojsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemoJspApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoJspApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(DemoJspApplication.class);
	}
}

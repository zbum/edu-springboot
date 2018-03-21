package com.example.webappoauthresourcesample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableResourceServer
@RestController
public class Oauth2ResourceApplication extends ResourceServerConfigurerAdapter{

	@GetMapping("/")
	public String hello() {
		return "hello!!";
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/").authenticated();
	}

	public static void main(String[] args) {
		SpringApplication.run(Oauth2ResourceApplication.class, args);
	}
}

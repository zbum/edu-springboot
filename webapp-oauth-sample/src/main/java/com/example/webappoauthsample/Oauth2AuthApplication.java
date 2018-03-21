package com.example.webappoauthsample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * $ curl 123:456@localhost:8080/oauth/token -d grant_type=password -d username=user -d password=a55905b8-5c78-4fbe-b658-a8bea1105c91 -d scope=all
 * $ curl -u 123:456 http://localhost:8080/oauth/token -d grant_type=password -d username=user -d password=a55905b8-5c78-4fbe-b658-a8bea1105c91 -d scope=all
 */

@SpringBootApplication
@EnableAuthorizationServer
@RestController
public class Oauth2AuthApplication {

	@GetMapping("/user")
	public Principal user(Principal user) {
		return user;
	}

	public static void main(String[] args) {
		SpringApplication.run(Oauth2AuthApplication.class, args);
	}
}

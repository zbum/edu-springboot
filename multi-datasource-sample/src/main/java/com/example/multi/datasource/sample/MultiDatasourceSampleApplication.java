package com.example.multi.datasource.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MultiDatasourceSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiDatasourceSampleApplication.class, args);
	}
}

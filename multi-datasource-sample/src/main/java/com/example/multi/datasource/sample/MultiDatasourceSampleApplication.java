package com.example.multi.datasource.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MultiDatasourceSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultiDatasourceSampleApplication.class, args);
    }

    @Bean
    @ConfigurationProperties("app.datasource.first")
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("app.datasource.second")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }
}

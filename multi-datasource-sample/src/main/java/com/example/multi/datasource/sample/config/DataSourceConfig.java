package com.example.multi.datasource.sample.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author myeongju.jung
 */
@Configuration
public class DataSourceConfig {
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

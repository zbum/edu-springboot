package com.nhnent.springboot.dooray.configure;

import com.nhnent.springboot.dooray.DoorayHookSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConditionalOnClass({DoorayHookSender.class})
@ConditionalOnBean(RestTemplate.class)
@EnableConfigurationProperties(DoorayProperties.class)
@ConditionalOnProperty(prefix = "dooray", name = {"hook-url"}, matchIfMissing = true)
public class DoorayAutoConfiguration {

    @Autowired
    DoorayProperties doorayProperties;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public DoorayHookSender doorayHookSender() {
        return new DoorayHookSender(restTemplate, doorayProperties.getHookUrl());
    }
}

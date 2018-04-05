package com.example.test.practice.config;

import com.example.test.practice.account.Account;
import com.example.test.practice.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author myeongju.jung
 */
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Configuration
public class SampleConfig {
    @Autowired
    AccountRepository accountRepository;

    @Bean
    CommandLineRunner createAccounts() {
        return (String... args) -> accountRepository.save(Arrays.asList(
            Account.forCreate("mandoo", "만두"),
            Account.forCreate("jordan", "조던"),
            Account.forCreate("dongmyo", "동묘"),
            Account.forCreate("doogi", "두기"),
            Account.forCreate("comtin", "콤틴"),
            Account.forCreate("franky", "프랑키"),
            Account.forCreate("manti", "만티"),
            Account.forCreate("bureung", "부릉")));
    }
}

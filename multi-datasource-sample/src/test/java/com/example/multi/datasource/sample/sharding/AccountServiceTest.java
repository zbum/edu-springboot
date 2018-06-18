package com.example.multi.datasource.sample.sharding;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

/**
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("shard")
public class AccountServiceTest {
    @Autowired
    AccountService service;

    @SpyBean(name = "firstDataSource")
    DataSource firstDataSource;
    @SpyBean(name = "secondDataSource")
    DataSource secondDataSource;

    @Before
    public void setUp() {
        reset(firstDataSource);
        reset(secondDataSource);
    }

    @Test
    public void createAndGetAccount_first() throws SQLException {
        // create
        Account account = new Account(2L, "account1@domain.com", "내용");
        service.create(account);
        Long accountId = account.getAccountId();
        then(firstDataSource).should(times(1)).getConnection();
        then(secondDataSource).should(never()).getConnection();

        // getAccount
        Account result = service.getAccount(accountId);
        assertThat(result, is(account));
        // 1번 Db에 연결된다.
        then(firstDataSource).should(times(2)).getConnection();
        then(secondDataSource).should(never()).getConnection();
    }

    @Test
    public void createAndGetAccount_second() throws SQLException {
        // create
        Account account = new Account(3L, "account2@domain.com", "내용");
        service.create(account);
        Long accountId = account.getAccountId();
        then(firstDataSource).should(never()).getConnection();
        then(secondDataSource).should(times(1)).getConnection();

        // getAccount
        Account result = service.getAccount(accountId);
        assertThat(result, is(account));
        // 2번 Db에 연결된다.
        then(firstDataSource).should(never()).getConnection();
        then(secondDataSource).should(times(2)).getConnection();
    }
}

package com.example.test.practice.account.application;

import com.example.test.practice.account.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)    // !!
public class AccountServiceTest {
    @Autowired
    AccountService service;

    @Test
    public void getAccounts() {
        // given
        // when
        List<Account> accounts = service.getAccounts();
        // then
        assertThat(accounts, hasSize(8));
        accounts.forEach(this::assertAccount);
    }

    private void assertAccount(Account account) {
        assertThat(account.getAccountId(), is(notNullValue()));
        assertThat(account.getUserId(), is(notNullValue()));
        assertThat(account.getName(), is(notNullValue()));
        assertThat(account.getCreatedAt(), is(notNullValue()));
    }

    /**
     * 1번 계정을 조회하고 검증하는 테스트케이스
     * Hint : {@link com.example.test.practice.config.SampleConfig}
     */
    @Test
    public void getAccount() {
        // given
        Long accountId = 1L;
        // when
        Account account = service.getAccount(accountId);
        // then
        assertThat(account.getAccountId(), is(accountId));
        // FIXME 2-1 : 통과하게 수정요망
        assertThat(account.getUserId(), is(""));
        assertThat(account.getName(), is(""));
        assertThat(account.getCreatedAt(), is(lessThan(null)));
    }
}

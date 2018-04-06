package com.example.multi.datasource.sample.sharding;

import com.example.multi.datasource.sample.sharding.aspect.Sharding;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author myeongju.jung
 */
@Service
@Profile("shard")
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Sharding(key = "#account.accountId")
    @Transactional
    public void create(Account account) {
        accountRepository.save(account);
    }

    @Sharding(key = "#accountId")
    public Account getAccount(Long accountId) {
        return accountRepository.findOne(accountId);
    }
}

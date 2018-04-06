package com.example.multi.datasource.sample.sharding;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author myeongju.jung
 */
@Profile("shard")
public interface AccountRepository extends JpaRepository<Account, Long> {
}

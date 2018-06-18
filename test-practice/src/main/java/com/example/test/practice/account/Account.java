package com.example.test.practice.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * @author myeongju.jung
 */
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long accountId;
    private String userId;
    private String name;
    private ZonedDateTime createdAt;

    @SuppressWarnings("WeakerAccess")
    Account() {
    }

    public static Account forCreate(String userId, String name) {
        Account result = new Account();
        result.userId = userId;
        result.name = name;
        result.createdAt = ZonedDateTime.now();
        return result;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Account{" +
               "accountId=" + accountId +
               ", userId='" + userId + '\'' +
               ", name='" + name + '\'' +
               ", createdAt=" + createdAt +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(accountId, account.accountId) &&
               Objects.equals(userId, account.userId) &&
               Objects.equals(name, account.name) &&
               Objects.equals(createdAt, account.createdAt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(accountId, userId, name, createdAt);
    }
}

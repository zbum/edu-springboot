package com.example.multi.datasource.sample.sharding;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author myeongju.jung
 */
@Entity
public class Account {
    @Id
    private Long accountId;
    private String email;
    private String name;

    @SuppressWarnings("unused")
    Account() {
    }

    @SuppressWarnings("WeakerAccess")
    public Account(Long accountId, String email, String name) {
        this.accountId = accountId;
        this.email = email;
        this.name = name;
    }

    @SuppressWarnings("WeakerAccess")
    public Long getAccountId() {
        return accountId;
    }

    @SuppressWarnings("unused")
    public String getEmail() {
        return email;
    }

    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Account{" +
               "accountId=" + accountId +
               ", email='" + email + '\'' +
               ", name='" + name + '\'' +
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
               Objects.equals(email, account.email) &&
               Objects.equals(name, account.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(accountId, email, name);
    }
}

package com.example.test.practice.account;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.ZonedDateTime;

/**
 * @author myeongju.jung
 */
@Entity
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Account {
    @Id
    @GeneratedValue
    private Long accountId;
    private String userId;
    private String name;
    private ZonedDateTime createdAt;

    public static Account forCreate(String userId, String name) {
        Account result = new Account();
        result.userId = userId;
        result.name = name;
        result.createdAt = ZonedDateTime.now();
        return result;
    }
}

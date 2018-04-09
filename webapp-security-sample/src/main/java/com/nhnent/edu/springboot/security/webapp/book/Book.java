package com.nhnent.edu.springboot.security.webapp.book;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author myeongju.jung
 */
@SuppressWarnings("WeakerAccess")
@Entity
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    @Setter
    private String title;
    private String writer;

    public static Book forCreate(@NonNull String title, @NonNull String writer) {
        Book result = new Book();
        result.title = title;
        result.writer = writer;
        return result;
    }
}

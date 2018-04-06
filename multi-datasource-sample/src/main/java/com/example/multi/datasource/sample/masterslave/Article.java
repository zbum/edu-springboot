package com.example.multi.datasource.sample.masterslave;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * @author myeongju.jung
 */
@SuppressWarnings("ALL")
@Entity
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Article {
    @Id
    @GeneratedValue
    private Long articleId;
    private String title;
    @Lob
    private String content;

    public static Article forCreate(String title, String content) {
        Article result = new Article();
        result.title = title;
        result.content = content;
        return result;
    }
}

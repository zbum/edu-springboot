package com.example.multi.datasource.sample.masterslave;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Objects;

/**
 * @author myeongju.jung
 */
@SuppressWarnings("ALL")
@Entity
public class Article {
    @Id
    @GeneratedValue
    private Long articleId;
    private String title;
    @Lob
    private String content;

    Article() {
    }

    public static Article forCreate(String title, String content) {
        Article result = new Article();
        result.title = title;
        result.content = content;
        return result;
    }

    public Long getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Article{" +
               "articleId=" + articleId +
               ", title='" + title + '\'' +
               ", content='" + content + '\'' +
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
        Article article = (Article) o;
        return Objects.equals(articleId, article.articleId) &&
               Objects.equals(title, article.title) &&
               Objects.equals(content, article.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(articleId, title, content);
    }
}

package com.nhnent.edu.springboot.security.webapp.book;

import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author myeongju.jung
 */
@SuppressWarnings("WeakerAccess")
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String writer;

    Book() {
    }

    public static Book forCreate(@NonNull String title, @NonNull String writer) {
        Book result = new Book();
        result.title = title;
        result.writer = writer;
        return result;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
               Objects.equals(title, book.title) &&
               Objects.equals(writer, book.writer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, writer);
    }

    @Override
    public String toString() {
        return "Book{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", writer='" + writer + '\'' +
               '}';
    }
}

package com.nhnent.edu.springboot.security.webapp.book;

import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * @author myeongju.jung
 */
@SuppressWarnings("WeakerAccess")
@Service
@Transactional(readOnly = true)
@Profile("book")
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 단순 Role 체크
    @PreAuthorize("hasRole('ROLE_WRITE')")
    @Transactional
    public void addBook(Book book) {
        bookRepository.save(book);
    }

    // 반환 객체로 비교하는 것도 가능
    @PostAuthorize("returnObject.writer == authentication.name")
    public Book getBook(Long id) {
        return bookRepository.findOne(id);
    }

    // 인자나 인자의 속성의 비교하는 것도 가능
    @PreAuthorize ("hasRole('ROLE_WRITE') && #book.writer == authentication.name")
    @Transactional
    public void changeBook(Book book) {
        Assert.notNull(book.getId(), "book.id must not be null");
        bookRepository.save(book);
    }

    // bean 자체를 주입받는 것도 가능 with SpEL. "?." = Null safety
    @PreAuthorize("@bookRepository.findOne(#id)?.writer == authentication?.name")
    @Transactional
    public void deleteBookById(Long id) {
        bookRepository.delete(id);
    }
}

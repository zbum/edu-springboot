package com.nhnent.edu.springboot.security.webapp.book;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author myeongju.jung
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("book")
public class BookServiceTest {
    @Autowired
    BookService bookService;
    @Autowired
    BookRepository bookRepository;

    @WithMockUser(roles = "WRITE")
    @Test
    public void addBook() {
        // given
        Book book = Book.forCreate("제목", "user");
        // when
        bookService.addBook(book);
        // then
    }

    @WithMockUser(roles = "READ")
    @Test(expected = AccessDeniedException.class)
    public void addBook_noRule() {
        // given
        Book book = Book.forCreate("제목", "user");
        // when
        bookService.addBook(book);
        // then
    }

    @WithMockUser(username = "user")
    @Test
    public void getBook() {
        // given
        Book saved = bookRepository.save(Book.forCreate("제목", "user"));
        // when
        Book result = bookService.getBook(saved.getId());
        // then
        assertThat(result, is(saved));
    }

    @WithMockUser(username = "anotherUser")
    @Test(expected = AccessDeniedException.class)
    public void getBook_anotherUser() {
        // given
        Book saved = bookRepository.save(Book.forCreate("제목", "user"));
        // when
        @SuppressWarnings("unused")
        Book result = bookService.getBook(saved.getId());
        // then
    }

    @WithMockUser(username = "user", roles = "WRITE")
    @Test
    public void changeBook() {
        // given
        Book saved = bookRepository.save(Book.forCreate("제목", "user"));
        saved.setTitle("새로운 제목");
        // when
        bookService.changeBook(saved);
        // then
    }

    @WithMockUser(username = "anotherUser", roles = "WRITE")
    @Test(expected = AccessDeniedException.class)
    public void changeBook_anotherUser() {
        // given
        Book saved = bookRepository.save(Book.forCreate("제목", "user"));
        saved.setTitle("새로운 제목");
        // when
        bookService.changeBook(saved);
        // then
    }

    @WithMockUser(username = "user", roles = "READ")
    @Test(expected = AccessDeniedException.class)
    public void changeBook_noRule() {
        // given
        Book saved = bookRepository.save(Book.forCreate("제목", "user"));
        saved.setTitle("새로운 제목");
        // when
        bookService.changeBook(saved);
        // then
    }

    @WithMockUser(username = "user")
    @Test
    public void deleteBookById() {
        // given
        Book saved = bookRepository.save(Book.forCreate("제목", "user"));
        // when
        bookService.deleteBookById(saved.getId());
        // then
    }

    @WithMockUser(username = "user")
    @Test(expected = AccessDeniedException.class)
    public void deleteBookById_notExists() {
        // given
        // when
        bookService.deleteBookById(0L); // 이건 좀 이슈가 있다. 존재하지 않으므로 404가 나는 것이 더 나을 수도 있다.
        // then
    }
}

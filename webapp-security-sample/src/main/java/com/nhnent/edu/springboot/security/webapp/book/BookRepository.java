package com.nhnent.edu.springboot.security.webapp.book;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author myeongju.jung
 */
@Profile("book")
public interface BookRepository extends JpaRepository<Book, Long> {
}

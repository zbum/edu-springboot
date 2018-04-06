package com.example.multi.datasource.sample.masterslave;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author myeongju.jung
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {
}

package com.example.multi.datasource.sample.masterslave;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author myeongju.jung
 */
@Profile("masterSlave")
public interface ArticleRepository extends JpaRepository<Article, Long> {
}

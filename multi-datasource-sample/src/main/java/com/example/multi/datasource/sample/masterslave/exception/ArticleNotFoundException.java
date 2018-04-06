package com.example.multi.datasource.sample.masterslave.exception;

/**
 * @author myeongju.jung
 */
public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(Long articleId) {
        super("Not found article : " + articleId);
    }
}

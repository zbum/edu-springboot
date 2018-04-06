package com.example.multi.datasource.sample.masterslave;

import com.example.multi.datasource.sample.masterslave.exception.ArticleNotFoundException;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author myeongju.jung
 */
@SuppressWarnings("WeakerAccess")
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Transactional
    public void create(@NonNull Article article) {
        articleRepository.save(article);
    }

    @Transactional(readOnly = true)
    public Article getArticle(@NonNull Long articleId) {
        return Optional.ofNullable(articleRepository.findOne(articleId))
                       .orElseThrow(() -> new ArticleNotFoundException(articleId));
    }
}

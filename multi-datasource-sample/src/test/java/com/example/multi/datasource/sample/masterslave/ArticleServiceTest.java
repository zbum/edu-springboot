package com.example.multi.datasource.sample.masterslave;

import com.example.multi.datasource.sample.masterslave.exception.ArticleNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

/**
 * @author myeongju.jung
 */
@SuppressWarnings("WeakerAccess")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("masterSlave")
@DirtiesContext
@Slf4j
public class ArticleServiceTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    ArticleService service;
    @SpyBean(name = "firstDataSource")
    DataSource writeDataSource;
    @SpyBean(name = "secondDataSource")
    DataSource readDataSource;
    @SpyBean(name = "routingDataSource")
    DataSource routingDataSource;
    @SpyBean
    DataSource dataSource;

    // param
    Long articleId;

    @Before
    public void setUp() {
        reset(writeDataSource);
        reset(readDataSource);
        reset(routingDataSource);
        reset(dataSource);
    }

    @Test
    public void create() throws SQLException {
        // given
        Article article = Article.forCreate("제목", "내용");
        // when
        service.create(article);
        // then
        articleId = article.getArticleId();
        then(writeDataSource).should(times(1)).getConnection();
        then(readDataSource).should(never()).getConnection();
        then(routingDataSource).should(times(1)).getConnection();
        then(dataSource).should(times(1)).getConnection();
    }

    @Test
    public void getArticle() throws SQLException {
        // given
        expectedException.expect(ArticleNotFoundException.class);
        // when
        service.getArticle(0L);
        // then
        then(readDataSource).should(times(1)).getConnection();
        then(writeDataSource).should(never()).getConnection();
        then(routingDataSource).should(times(1)).getConnection();
        then(dataSource).should(times(1)).getConnection();
    }

    @Test
    public void createAndGetArticle() throws SQLException {
        // create
        Article article = Article.forCreate("제목", "내용");
        service.create(article);

        then(writeDataSource).should(times(1)).getConnection();
        then(readDataSource).should(never()).getConnection();
        then(routingDataSource).should(times(1)).getConnection();
        then(dataSource).should(times(1)).getConnection();

        Long articleId = article.getArticleId();

        // getArticle
        Article result = service.getArticle(articleId);
        log.info("result = {}", result);

        assertThat(result, is(notNullValue()));
        then(writeDataSource).should(times(1)).getConnection();
        then(readDataSource).should(times(1)).getConnection();
        then(routingDataSource).should(times(2)).getConnection();
        then(dataSource).should(times(2)).getConnection();
    }
}

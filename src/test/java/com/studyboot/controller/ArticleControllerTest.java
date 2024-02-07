package com.studyboot.controller;

import com.studyboot.model.Article;
import com.studyboot.repository.ArticleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    ArticleController articleController;

    @MockBean
    ArticleRepository articleRepository;
    List<Article> articleList = new ArrayList<>();
    @BeforeEach
    void setUp(){
        articleController = new ArticleController(articleRepository);
        articleList = List.of(new Article(1,"hello bhutan","i'm from bhutan"));
    }

    @Test
    void shouldReturnAllArticles() {
        Mockito.when(articleRepository.findAll()).thenReturn(articleList);
        Assertions.assertEquals(1,articleController.findAll().size());
    }

    @Test
    void shoudReturnArticleWithValidId() {
        Mockito.when(articleRepository.findById(1)).thenReturn(articleList.get(0));
        Article article = articleController.findById(1);
        Assertions.assertNotNull(article);
    }
}
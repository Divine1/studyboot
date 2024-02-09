package com.studyboot.controller;


import com.studyboot.common.Common;
import com.studyboot.model.Article;
import com.studyboot.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/api/articles")
public class ArticleController {

    Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository){
        this.articleRepository=articleRepository;
    }

    @GetMapping
    public CompletableFuture<List<Article>> findAll(){
        //Common.sleep(20);
        // http://localhost:8080/api/articles
        logger.info("controller findall - before");
        CompletableFuture<List<Article>> cf =  articleRepository.findAll();
         logger.info(Thread.currentThread().getName());
        logger.info("controller findall - after");
         //return List.of(new Article(100,"test","testt"));
        return cf;
    }

    @GetMapping(value ="/{id}")
    public Article findById(@PathVariable Integer id){

        return articleRepository.findById(id);
    }
}

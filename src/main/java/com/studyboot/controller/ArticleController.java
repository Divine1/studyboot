package com.studyboot.controller;


import com.studyboot.model.Article;
import com.studyboot.repository.ArticleRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/articles")
public class ArticleController {

    private ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository){
        this.articleRepository=articleRepository;
    }

    @GetMapping
    public List<Article> findAll(){
        return articleRepository.findAll();
    }

    @GetMapping(value ="/{id}")
    public Article findById(@PathVariable Integer id){
        return articleRepository.findById(id);
    }
}

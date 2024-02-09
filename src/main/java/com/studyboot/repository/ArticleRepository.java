package com.studyboot.repository;

import com.studyboot.common.Common;
import com.studyboot.controller.ArticleController;
import com.studyboot.model.Article;
import com.studyboot.service.SlugService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Repository
public class ArticleRepository {

    Logger logger = LoggerFactory.getLogger(ArticleRepository.class);

    List<Article> articleList;
    private SlugService slugService;



    public ArticleRepository(SlugService slugService){
        this.slugService=slugService;

        articleList = List.of(
                new Article(1,"hello world","i'm from india"),
                new Article(2,"hello japan","i'm from tokyo"),
                new Article(3,"Hello usa","i'm from usa")
        );
        Iterator<Article> it = articleList.iterator();
        while(it.hasNext()){
            Article temp = it.next();
            temp.setSlug(slugService.slugify(temp.getTitle()));
        }
    }

    @Async
    public CompletableFuture<List<Article>> findAll(){

        logger.info("repository findall - before");
        Common.sleep(5);
        CompletableFuture<List<Article>> cf = CompletableFuture.supplyAsync(()->{
            logger.info("repository findall - executing");
            logger.info("inside {}",Thread.currentThread().getName());
            return this.articleList;
        });
        logger.info(Thread.currentThread().getName());
        logger.info("repository findall - after");
        return cf;
    }
    public Article findById(Integer id){
        return articleList.stream().filter(article -> {
            return article.getId().equals(id);
        }).findFirst().orElse(null);
    }
}

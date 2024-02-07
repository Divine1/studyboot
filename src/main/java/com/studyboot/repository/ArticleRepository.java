package com.studyboot.repository;

import com.studyboot.model.Article;
import com.studyboot.service.SlugService;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleRepository {
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
    public List<Article> findAll(){
        return this.articleList;
    }
    public Article findById(Integer id){
        return articleList.stream().filter(article -> {
            return article.getId().equals(id);
        }).findFirst().orElse(null);
    }
}

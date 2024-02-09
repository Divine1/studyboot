package com.studyboot.controller;

import com.studyboot.model.Post;
import com.studyboot.service.JsonPlaceholderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    StringBuilder stringBuilder = new StringBuilder();
    JsonPlaceholderService jsonPlaceholderService;


    public PostController(JsonPlaceholderService jsonPlaceholderService){
        this.jsonPlaceholderService=jsonPlaceholderService;
    }

    @GetMapping(value = "")
    List<Post> findAll(){

        return jsonPlaceholderService.findAll();
    }
    @GetMapping(value = "/{id}")
    Post findById(@PathVariable Integer id){
        Post post = jsonPlaceholderService.findById(id);
        stringBuilder.append(post);
        return post;
    }


}

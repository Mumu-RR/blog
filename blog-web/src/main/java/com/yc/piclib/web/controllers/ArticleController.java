package com.yc.piclib.web.controllers;

import com.yc.piclib.future.ArticleFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class ArticleController {
    private static Logger logger = LoggerFactory.getLogger(ArticleController.class.getName());

    @Autowired
    private ArticleFuture articleFuture;

    @GetMapping("/getArticleById")
    public CompletableFuture<String> getArticleById(@RequestParam("id")int id){
        return articleFuture.getArticleById(id);
    }

    @GetMapping("/selectAll")
    public CompletableFuture<String> selectAll(){
        return articleFuture.selectAll();
    }
}

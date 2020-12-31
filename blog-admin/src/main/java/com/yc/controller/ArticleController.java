package com.yc.controller;

import com.yc.blog.entity.Article;
import com.yc.piclib.future.ArticleFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
public class ArticleController {

    private static Logger logger = LoggerFactory.getLogger(ArticleController.class.getName());

    @Autowired
    private ArticleFuture articleFuture;

    @GetMapping("/selectAll")
    public CompletableFuture<String> selectAll(){
        return articleFuture.selectAll();
    }

    @GetMapping("/deleteArticleById")
    public CompletableFuture<String> deleteArticleById(@RequestParam("id")int id){
        return articleFuture.deleteArticleById(id);
    }

    @GetMapping("/getArticleById")
    public CompletableFuture<String> getArticleById(@RequestParam("id")int id){
        return articleFuture.getArticleById(id);
    }

    @RequestMapping("/updateArticle")
    public CompletableFuture<String> updateCategory(@RequestBody Article article) {
        int id = article.getId();
        String title = article.getTitle();
        String content = article.getContent();
        String label = article.getLabel();
        int categoryid = article.getCategoryid();
        return articleFuture.updateArticle(id,title,content,label,categoryid);
    }
}

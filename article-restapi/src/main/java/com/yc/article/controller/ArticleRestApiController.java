package com.yc.article.controller;

import com.google.gson.Gson;
import com.yc.blog.dao.impl.ArticleMapper;
import com.yc.blog.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping
public class ArticleRestApiController {

    private static Logger logger = LoggerFactory.getLogger(ArticleRestApiController.class);

    @Resource
    private ArticleMapper am;

    @GetMapping("/getArticleById")
    public String getArticleById(@RequestParam("id")int id){
        Article article = am.selectById(id);
        System.out.println("成功运行到此处");
        return new Gson().toJson(article);
    }

    @GetMapping("/selectAll")
    public String selectAll(){
        List<Article> articleList = am.selectAll();
        return new Gson().toJson(articleList);
    }

    @RequestMapping("/addArticle")
    public String addArticle(@RequestParam("title")String titlt,@RequestParam("categoryid")int categoryid,@RequestParam("label")String label,@RequestParam("content")String content){
        return null;
    }


}

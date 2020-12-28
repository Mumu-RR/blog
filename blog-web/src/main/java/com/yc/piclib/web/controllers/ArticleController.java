package com.yc.piclib.web.controllers;

import com.yc.blog.entity.User;
import com.yc.piclib.future.ArticleFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
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

    @RequestMapping("/addArticle")
    public CompletableFuture<String> addArticle(HttpSession session, @RequestParam("title")String title,
                                                @RequestParam("categoryid")int categoryid, @RequestParam("label")String label,
                                                @RequestParam("content")String content){
        User user=(User)session.getAttribute("loginedUser");
        String account = user.getAccount();
        System.out.println("web:"+account+"|"+title+"|"+categoryid+"|"+label+"|"+content);
        return articleFuture.addArticle(account,title,categoryid,label,content);
    }

    @GetMapping("/hotArticle")
    public CompletableFuture<String> hotArticle(){
        return articleFuture.hotArticle();
    }
}

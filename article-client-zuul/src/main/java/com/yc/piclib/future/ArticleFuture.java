package com.yc.piclib.future;

import com.yc.piclib.Articleservice.ArticleRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.CompletableFuture;

// 对外公开的业务层
@Component
public class ArticleFuture {
    @Autowired
    private ArticleRestService articleRestService;

    @Async
    public CompletableFuture<String> getArticleById(@RequestParam("id")int id){
        return CompletableFuture.supplyAsync(() -> {
            return articleRestService.getArticleById(id);
        });
    }

    @Async
    public CompletableFuture<String> selectEight(){
        return CompletableFuture.supplyAsync(() -> {
            return articleRestService.selectEight();
        });
    }

    @Async
    public CompletableFuture<String> selectAll(){
        return CompletableFuture.supplyAsync(() -> {
            return articleRestService.selectAll();
        });
    }

    @Async
    public CompletableFuture<String> addArticle(@RequestParam("account")String account, @RequestParam("title")String title,
                                                @RequestParam("categoryid")int categoryid, @RequestParam("label")String label,
                                                @RequestParam("content")String content){
        return CompletableFuture.supplyAsync(() -> {
            return articleRestService.addArticle(account,title,categoryid,label,content);
        });
    }

    @Async
    public CompletableFuture<String> hotArticle(){
        return CompletableFuture.supplyAsync(() -> {
            return articleRestService.hotArticle();
        });
    }

    @Async
    public CompletableFuture<String> deleteArticleById(@RequestParam("id")int id){
        return CompletableFuture.supplyAsync(() -> {
            return articleRestService.deleteArticleById(id);
        });
    }

    @Async
    public CompletableFuture<String> addNewNotice(@RequestParam("title")String title,@RequestParam("content") String content, @RequestParam("label")String label) {
        return CompletableFuture.supplyAsync(() -> {
            return articleRestService.addNewNotice(title,content,label);
        });
    }
}

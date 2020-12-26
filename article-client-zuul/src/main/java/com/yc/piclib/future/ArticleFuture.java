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
    public CompletableFuture<String> selectAll(){
        return CompletableFuture.supplyAsync(() -> {
            return articleRestService.selectAll();
        });
    }
}

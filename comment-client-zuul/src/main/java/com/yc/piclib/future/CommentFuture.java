package com.yc.piclib.future;

import com.yc.piclib.Commentservice.CommentRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.CompletableFuture;

// 对外公开的业务层
@Component
public class CommentFuture {
    @Autowired
    private CommentRestService commentRestService;

    @Async
    public CompletableFuture<String> selectByArticleId(@RequestParam("id")int id) {
        return CompletableFuture.supplyAsync(() -> {
            return commentRestService.selectByArticleId(id);
        });
    }


    @Async
    public CompletableFuture<String> create(@RequestParam("account")String account, @RequestParam("id")int id, @RequestParam("content")String content) {
        return CompletableFuture.supplyAsync(() -> {
            return commentRestService.create(account,id,content);
        });
    }
}

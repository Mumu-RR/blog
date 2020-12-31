package com.yc.controller;

import com.yc.piclib.future.CommentFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class CommentController {
    private static Logger logger = LoggerFactory.getLogger(CommentController.class.getName());

    @Autowired
    private CommentFuture commentFuture;

    @RequestMapping("/selectComment")
    public CompletableFuture<String> selectComment(){
        return commentFuture.selectComment();
    }

    @GetMapping("/deleteCommentById")
    public CompletableFuture<String> deleteCommentById(@RequestParam("id")int id) {
        return commentFuture.deleteCommentById(id);
    }
}

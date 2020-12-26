package com.yc.piclib.web.controllers;

import com.yc.blog.entity.Comment;
import com.yc.blog.entity.User;
import com.yc.piclib.future.CommentFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
public class CommentController {
    private static Logger logger = LoggerFactory.getLogger(CommentController.class.getName());

    @Autowired
    private CommentFuture commentFuture;

    @GetMapping("queryComm")
    public CompletableFuture<String> selectByArticleId(@RequestParam("id")int id){
        return commentFuture.selectByArticleId(id);
    }

    @RequestMapping(method = RequestMethod.POST,value = "/reply.do")
    public CompletableFuture<String> create(@Valid Comment comment, Errors errors, @SessionAttribute User loginedUser, @RequestParam("id")int id, @RequestParam("content")String content){
        System.out.println("web层："+comment+"||"+errors+"||"+loginedUser+"||"+id+"||"+content);
        return commentFuture.create(comment,errors,loginedUser,id,content);
    }
}

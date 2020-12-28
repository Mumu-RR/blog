package com.yc.piclib.web.controllers;

import com.yc.blog.entity.User;
import com.yc.piclib.future.CommentFuture;
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
public class CommentController {
    private static Logger logger = LoggerFactory.getLogger(CommentController.class.getName());

    @Autowired
    private CommentFuture commentFuture;

    @GetMapping("queryComm")
    public CompletableFuture<String> selectByArticleId(@RequestParam("id")int id){
        return commentFuture.selectByArticleId(id);
    }

    @RequestMapping("/reply.do")
    public CompletableFuture<String> create(HttpSession session,  @RequestParam("id")int id, @RequestParam("content")String content){
        System.out.println("web层："+id+"||"+content);
        User user=(User)session.getAttribute("loginedUser");
        String account = user.getAccount();
        return commentFuture.create(account,id,content);
    }
}

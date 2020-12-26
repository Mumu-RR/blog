package com.yc.piclib.Commentservice;

import com.yc.blog.entity.Comment;
import com.yc.blog.entity.User;
import com.yc.piclib.client.CommentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.validation.Valid;

@Service
public class CommentRestService {
    @Autowired
    private CommentClient commentClient;

    public String selectByArticleId(@RequestParam("id")int id) {
        return commentClient.selectByArticleId(id);
    }

    public String create(@Valid Comment comment, Errors errors, @SessionAttribute User loginedUser, @RequestParam("id")int id, @RequestParam("content")String content) {
        System.out.println("client层："+comment+"||"+errors+"||"+loginedUser+"||"+id+"||"+content);
        return commentClient.create(comment,errors,loginedUser,id,content);
    }
}

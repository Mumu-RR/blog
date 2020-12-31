package com.yc.piclib.Commentservice;

import com.yc.piclib.client.CommentClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class CommentRestService {
    @Autowired
    private CommentClient commentClient;

    public String selectByArticleId(@RequestParam("id")int id) {
        return commentClient.selectByArticleId(id);
    }

    public String create(@RequestParam("account")String account, @RequestParam("id")int id, @RequestParam("content")String content) {
        System.out.println("client层："+id+"||"+content);
        return commentClient.create(account,id,content);
    }

    public String selectComment() {
        return commentClient.selectComment();
    }

    public String deleteCommentById(@RequestParam("id")int id) {
        return commentClient.deleteCommentById(id);
    }
}

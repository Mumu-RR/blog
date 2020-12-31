package com.yc.blog.controller;

import com.google.gson.Gson;
import com.yc.blog.dao.impl.CommentMapper;
import com.yc.blog.dao.impl.UserMapper;
import com.yc.blog.entity.Comment;
import com.yc.blog.entity.Result;
import com.yc.blog.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CommentRestApiController {
    private static Logger logger = LoggerFactory.getLogger(CommentRestApiController.class);

    @Resource
    private CommentMapper cm;

    @Resource
    private UserMapper um;

    @GetMapping("queryComm")
    public String selectByArticleId(@RequestParam("id")int id) {
        return new Gson().toJson(new Result(1,"评论查询成功！",cm.selectByArticleid(id)));
    }

    @GetMapping("/reply.do")
    public String create(@RequestParam("account")String account, @RequestParam("id")int id, @RequestParam("content")String content) {
//        if(errors.hasErrors()) {
//            return new Gson().toJson(new Result(0,"评论验证错误！",errors.getAllErrors()));
//        }
        User user = um.selectByAccount(account);
        Comment comment = new Comment();
        comment.setCreateby(user.getId());
        comment.setArticleid(id);
        comment.setContent(content);
        cm.insert(comment);
        return new Gson().toJson(new Result(1,"评论发表成功！",comment));
    }

    @GetMapping("/selectComment")
    public String selectComment(){
        List<Comment> commentList = cm.selectComment();
        return new Gson().toJson(commentList);
    }

    @GetMapping("deleteCommentById")
    public void deleteCommentById(@RequestParam("id")int id){
        cm.deleteCommentById(id);
    }
}

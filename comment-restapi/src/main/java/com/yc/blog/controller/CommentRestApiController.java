package com.yc.blog.controller;

import com.yc.blog.dao.impl.CommentMapper;
import com.yc.blog.entity.Comment;
import com.yc.blog.entity.Result;
import com.yc.blog.entity.User;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class CommentRestApiController {

    @Resource
    private CommentMapper cm;

    @GetMapping("queryComm")
    public Result selectByArticleId(int articleid) {
        return new Result(1,"评论查询成功！",cm.selectByArticleid(articleid));
    }

    @PostMapping("/reply.do")
    public Result create(@Valid Comment comment, Errors errors, @SessionAttribute User loginedUser, @RequestParam("articleid")int articleid, @RequestParam("content")String content) {
        System.out.println("reply.do111111111");
        if(errors.hasErrors()) {
            return new Result(0,"评论验证错误！",errors.getAllErrors());
        }
        comment.setCreateby(loginedUser.getId());
        cm.insert(comment);
        return new Result(1,"评论发表成功！",comment);
    }
}

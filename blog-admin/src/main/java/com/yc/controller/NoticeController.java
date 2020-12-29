package com.yc.controller;

import com.yc.piclib.future.ArticleFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class NoticeController {

    private static Logger logger = LoggerFactory.getLogger(NoticeController.class.getName());

    @Autowired
    private ArticleFuture articleFuture;

    @RequestMapping("/addNewNotice")
    public CompletableFuture<String> addNewNotice(@RequestParam("title")String title, @RequestParam("content")String content, @RequestParam("label")String label){
        return articleFuture.addNewNotice(title,content,label);
    }

    @GetMapping("/getNotice")
    public CompletableFuture<String> getNotice(){
        return articleFuture.getNotice();
    }

    @GetMapping("/deleteNoticeById")
    public CompletableFuture<String> deleteNoticeById(@RequestParam("id")int id){
        return articleFuture.deleteNoticeById(id);
    }
}

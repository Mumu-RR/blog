package com.yc.article.controller;

import com.google.gson.Gson;
import com.yc.blog.dao.impl.ArticleMapper;
import com.yc.blog.dao.impl.NoticeMapper;
import com.yc.blog.dao.impl.UserMapper;
import com.yc.blog.entity.Article;
import com.yc.blog.entity.Notice;
import com.yc.blog.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping
public class ArticleRestApiController {

    private static Logger logger = LoggerFactory.getLogger(ArticleRestApiController.class);

    @Resource
    private ArticleMapper am;

    @Resource
    private UserMapper um;

    @Resource
    private NoticeMapper nm;

    @GetMapping("/getArticleById")
    public String getArticleById(@RequestParam("id")int id){
        Article article = am.selectById(id);
        return new Gson().toJson(article);
    }

    @GetMapping("/selectEight")
    public String selectEight(){
        List<Article> articleList = am.selectEight();
        return new Gson().toJson(articleList);
    }

    @GetMapping("/selectAll")
    public String selectAll(){
        List<Article> articleList = am.selectAll();
        return new Gson().toJson(articleList);
    }

    @RequestMapping("/addArticle")
    public void addArticle(@RequestParam("account")String account, @RequestParam("title")String title,
                             @RequestParam("categoryid")int categoryid, @RequestParam("label")String label,
                             @RequestParam("content")String content, HttpServletResponse response){
        System.out.println("restapi:"+account+"|"+title+"|"+categoryid+"|"+label+"|"+content);
        User user = um.selectByAccount(account);
        String userName = user.getName();
        //添加进文章对象
        Article article = new Article();
        article.setAuthor(userName);
        article.setTitle(title);
        article.setContent(content);
        article.setCategoryid(categoryid);
        article.setLabel(label);
        am.insert(article);
        try {
            response.sendRedirect("http://localhost:8095/index.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/hotArticle")
    public String hotArticle(){
        List<Article> articleList = am.hotArticle();
        return new Gson().toJson(articleList);
    }

    @GetMapping("/deleteArticleById")
    public void deleteArticleById(@RequestParam("id")int id){
        am.deleteArticleById(id);
    }


    @RequestMapping("/updateArticle")
    public void updateArticle(@RequestParam("id")int id,@RequestParam("title") String title,
                                @RequestParam("content")String content,@RequestParam("label") String label,
                                @RequestParam("categoryid")int categoryid){
        Article article = new Article();
        article.setId(id);
        article.setTitle(title);
        article.setContent(content);
        article.setLabel(label);
        article.setCategoryid(categoryid);
        am.updataArticle(article);
    }





    //Notice
    @GetMapping("/addNewNotice")
    public void addNewNotice(@RequestParam("title")String title, @RequestParam("content")String content
            ,@RequestParam("label") String label,HttpServletResponse response){
        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setContent(content);
        notice.setLabel(label);
        nm.addNewNotice(notice);
        try {
            response.sendRedirect("http://localhost:8090/notice.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/getNotice")
    public String getNotice(){
        List<Notice> noticeList = nm.selectAll();
        return new Gson().toJson(noticeList);
    }

    @GetMapping("/deleteNoticeById")
    public void deleteNoticeById(@RequestParam("id")int id){
        nm.deleteNoticeById(id);
    }
}

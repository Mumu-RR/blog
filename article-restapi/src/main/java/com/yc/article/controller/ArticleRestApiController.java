package com.yc.article.controller;

import com.google.gson.Gson;
import com.yc.blog.dao.impl.ArticleMapper;
import com.yc.blog.dao.impl.UserMapper;
import com.yc.blog.entity.Article;
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

    @GetMapping("/getArticleById")
    public String getArticleById(@RequestParam("id")int id){
        Article article = am.selectById(id);
        return new Gson().toJson(article);
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


}

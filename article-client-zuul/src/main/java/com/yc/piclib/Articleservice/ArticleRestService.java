package com.yc.piclib.Articleservice;

import com.yc.piclib.client.ArticleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ArticleRestService {

    @Autowired
    private ArticleClient articleClient;

    public String getArticleById(@RequestParam("id")int id){
        return articleClient.getArticleById(id);
    }

    public String selectAll(){
        return articleClient.selectAll();
    }
}

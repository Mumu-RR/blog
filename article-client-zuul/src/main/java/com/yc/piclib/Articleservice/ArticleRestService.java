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

    public String selectEight(){
        return articleClient.selectEight();
    }

    public String selectAll(){
        return articleClient.selectAll();
    }

    public String addArticle(@RequestParam("account")String account, @RequestParam("title")String title,
                             @RequestParam("categoryid")int categoryid, @RequestParam("label")String label,
                             @RequestParam("content")String content){
        System.out.println("zuul:"+account+"|"+title+"|"+categoryid+"|"+label+"|"+content);
        return articleClient.addArticle(account,title,categoryid,label,content);
    }

    public String hotArticle(){
        return articleClient.hotArticle();
    }

    public String deleteArticleById(@RequestParam("id")int id){
        return articleClient.deleteArticleById(id);
    }







    public String addNewNotice(@RequestParam("title")String title, @RequestParam("content")String content,@RequestParam("label") String label) {
        System.out.println(title+"||"+content+"||"+label);
        return  articleClient.addNewNotice(title,content,label);
    }

    public String getNotice() {
        return articleClient.getNotice();
    }

    public String deleteNoticeById(@RequestParam("id")int id) {
        return articleClient.deleteNoticeById(id);
    }
}

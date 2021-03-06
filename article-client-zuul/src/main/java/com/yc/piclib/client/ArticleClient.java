package com.yc.piclib.client;

import com.yc.piclib.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// feign客户端要访问的是  zuul服务 BASE-MICROSERVICE-ZUUL-GATEWAY
@FeignClient(name = "BASE-MICROSERVICE-ZUUL-GATEWAY",
        configuration = FeignClientConfig.class
)  // 配置要按自定义的类FeignClientConfig
@Component
public interface ArticleClient {

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/article-proxy/getArticleById")
    String getArticleById(@RequestParam("id")int id);

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/article-proxy/selectEight")
    String selectEight();

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/article-proxy/selectAll")
    String selectAll();

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/article-proxy/addArticle")
    String addArticle( @RequestParam("account")String account, @RequestParam("title")String title,
                      @RequestParam("categoryid")int categoryid, @RequestParam("label")String label,
                      @RequestParam("content")String content);

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/article-proxy/hotArticle")
    String hotArticle();

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/article-proxy/deleteArticleById")
    String deleteArticleById(@RequestParam("id")int id);






    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/article-proxy/addNewNotice")
    String addNewNotice(@RequestParam("title")String title, @RequestParam("content")String content,@RequestParam("label") String label);

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/article-proxy/getNotice")
    String getNotice();

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/article-proxy/deleteNoticeById")
    String deleteNoticeById(@RequestParam("id")int id);

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/article-proxy/updateArticle")
    String updateArticle(@RequestParam("id")int id,@RequestParam("title") String title,
                         @RequestParam("content")String content,@RequestParam("label") String label,
                         @RequestParam("categoryid")int categoryid);
}

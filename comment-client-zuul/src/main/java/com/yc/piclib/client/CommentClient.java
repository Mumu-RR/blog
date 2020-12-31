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
public interface CommentClient {

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/comment-proxy/queryComm")
    String selectByArticleId(@RequestParam("id")int id);

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/comment-proxy/reply.do")
    String create(@RequestParam("account")String account,@RequestParam("id")int id, @RequestParam("content")String content);

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/comment-proxy/selectComment")
    String selectComment();

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/comment-proxy/deleteCommentById")
    String deleteCommentById(@RequestParam("id")int id);
}

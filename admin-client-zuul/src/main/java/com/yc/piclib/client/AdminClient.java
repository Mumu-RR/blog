package com.yc.piclib.client;

import com.yc.blog.entity.Admin;
import com.yc.piclib.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

// feign客户端要访问的是  zuul服务 BASE-MICROSERVICE-ZUUL-GATEWAY
@FeignClient(name = "BASE-MICROSERVICE-ZUUL-GATEWAY",
        configuration = FeignClientConfig.class
)  // 配置要按自定义的类FeignClientConfig
@Component
public interface AdminClient {

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/admin-proxy/login")
    String login(@RequestParam("admin") Admin admin, @RequestParam("errors") Errors errors,
                 @RequestParam("session") HttpSession session, @RequestParam("account")String account, @RequestParam("pwd")String pwd);

}

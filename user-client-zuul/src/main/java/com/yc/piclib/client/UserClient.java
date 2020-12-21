package com.yc.piclib.client;

import com.yc.blog.entity.User;
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
public interface UserClient {

    //访问的路径value要修改成zuul指定的服务路由路径
    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/user-proxy/login")
    String login(@RequestParam("user")User user, @RequestParam("errors") Errors errors,
                 @RequestParam("session") HttpSession session, @RequestParam("account")String account,@RequestParam("pwd")String pwd);


    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/user-proxy/register")
    String register(@RequestParam("user") User user, @RequestParam("errors")Errors errors, @RequestParam("account")String account,
                    @RequestParam("pwd")String pwd, @RequestParam("name")String name, @RequestParam("email")String email);

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/user-proxy/getQuestion")
    String getQuestion( @RequestParam("account")String account);

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/user-proxy/answer")
    String answer( @RequestParam("account")String account, @RequestParam("pwdAnswer")String pwdAnswer);

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/user-proxy/resetPwd")
    String resetPwd( @RequestParam("account")String account,@RequestParam("pwd") String pwd);


}





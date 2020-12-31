package com.yc.piclib.service;

import com.yc.blog.entity.Admin;
import com.yc.piclib.client.AdminClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
@Service
public class AdminRestService {

    //Hystrix服务层:  调用 PiclibClient,实现断路器功能
    @Autowired
    private AdminClient adminClient;

    public String login(@RequestParam("admin") Admin admin, @RequestParam("errors") Errors errors, @RequestParam("session") HttpSession session,
                        @RequestParam("account") String account, @RequestParam("pwd") String pwd) {

        return adminClient.login(admin, errors, session, account, pwd);
        }

    public String doCheck(@RequestParam("account")String account) {
        //System.out.println("zuul:"+admin);
        return adminClient.doCheck(account);
    }
}

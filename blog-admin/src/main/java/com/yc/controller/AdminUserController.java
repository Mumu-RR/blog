package com.yc.controller;

import com.yc.blog.entity.Admin;
import com.yc.piclib.future.AdminFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping
public class AdminUserController {
    private static Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @Autowired
    private AdminFuture adminFuture;

    @RequestMapping("/login")
    public CompletableFuture<String> login(@Valid Admin admin, Errors errors, HttpSession session, @RequestParam("account")String account, @RequestParam("pwd")String pwd) {
        // System.out.println("web层"+user+"errors:"+errors);;
        session.setAttribute("loginedAdmin", admin);
        return adminFuture.login(admin,errors,session,account,pwd);
    }

    @RequestMapping("/doCheck")
    public CompletableFuture<String> doCheck(HttpSession session) throws IOException {
        Admin admin=(Admin)session.getAttribute("loginedAdmin");
//        Map<String,Object> map=new HashMap<String,Object>();
//        map.put("admin", admin);
//        return new Gson().toJson(map);
        System.out.println("web:"+admin);
        String account = admin.getAccount();
        return adminFuture.doCheck(account);
    }
}

package com.yc.piclib.future;


import com.yc.blog.entity.Admin;
import com.yc.piclib.service.AdminRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

import java.util.concurrent.CompletableFuture;

// 对外公开的业务层
@Component
public class AdminFuture {
    @Autowired
    private AdminRestService adminRestService;

    @Async
    public CompletableFuture<String> login(Admin admin, Errors errors, HttpSession session, @RequestParam("account")String account, @RequestParam("pwd")String pwd) {
        return CompletableFuture.supplyAsync(() -> {
            return adminRestService.login(admin,errors,session,account,pwd);
        });
    }

    @Async
    public CompletableFuture<String> doCheck(@RequestParam("account")String account) {
        return CompletableFuture.supplyAsync(() -> {
            return adminRestService.doCheck(account);
        });
    }
}

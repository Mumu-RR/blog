package com.yc.admin.restapi.controllers;


import com.google.gson.Gson;
import com.yc.admin.restapi.service.AdminServiceImpl;
import com.yc.blog.dao.impl.AdminMapper;
import com.yc.blog.entity.Admin;
import com.yc.blog.entity.Result;
import com.yc.blog.util.BizException;
import com.yc.blog.util.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping
public class AdminRestController {
    private static Logger logger = LoggerFactory.getLogger(AdminRestController.class);

    @Autowired
    private AdminMapper am;
    @Autowired
    private AdminServiceImpl adminService;



    @RequestMapping("/login")
    public CompletableFuture<String> login(@Valid Admin admin, Errors errors, HttpSession session, @RequestParam("account")String account, @RequestParam("pwd")String pwd) {
        return CompletableFuture.supplyAsync(() -> {
            //用户输入验证
            if (errors.hasFieldErrors("account") || errors.hasFieldErrors("pwd")) {
               // return new Result(0, "字段验证错误", errors.getAllErrors());
                return new Gson().toJson(new Result(0, "字段验证错误", errors.getAllErrors()));
            }

            //业务逻辑验证
            try {
                //md5加密
                admin.setPwd(MD5Utils.stringToMD5(admin.getPwd()));
                Admin aduser =adminService.login(admin);
                //session.setAttribute("loginedUser", dbuser);
               // return new Result(1, "登录成功！");
                return new Gson().toJson(new Result(1, "登录成功！"));
            } catch (BizException e) {
                e.printStackTrace();
                errors.rejectValue("account", "AccountOrPwdFailure", e.getMessage());
                //return new Result(0, "业务逻辑验证错误", errors.getAllErrors());
                return new Gson().toJson(new Result(0, "业务逻辑验证错误", errors.getAllErrors()));
            }
        });
    }

}

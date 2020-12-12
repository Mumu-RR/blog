package com.yc.user.restapi.controllers;


import com.google.gson.Gson;
import com.yc.blog.Userservice.UserServiceImpl;
import com.yc.blog.entity.Result;
import com.yc.blog.entity.User;
import com.yc.blog.util.BizException;
import com.yc.blog.util.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping
public class UserRestController {
    private static Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/findAll")
    //  @HystrixCommand(fallbackMethod = "errorCallBack")   //模仿没有这个数据时，服务降级
    public CompletableFuture<String> findAll() {
        // static CompletableFuture<U> supplyAsync(Supplier<U> supplier)
        //   Supplier就是一个接口
        //    接口中的方法:   T get();

//        return CompletableFuture.supplyAsync(new Supplier() {
//            @Override
//            public Object get() {  //回调方法,   当请求有响应，由  jvm 调用.
//                  PicDomain pic = picService.findOne(id);
        //            Map<String, Object> map = new HashMap<>();
        //            map.put("code", 1);
        //            map.put("data", pic);
        //            return new Gson().toJson(map);
//            }
//        });
        //非阻塞式异步编程方法。因为在web ui的微服务对rest api的调用中将使用这种高并发的编程方法，所以为了保证与调用端保持同步，这里也使用这种方法.
        return CompletableFuture.supplyAsync(() -> {
           List<User> users =  userService.selectAll();
            //协议
            Map<String, Object> map = new HashMap<>();
            map.put("code", 1);
            map.put("data", users);
            //map.put("msg","");
            return new Gson().toJson(map);
        });
    }

    @RequestMapping("/login")
    public CompletableFuture<String> login(@Valid User user, Errors errors, HttpSession session, @RequestParam("account")String account, @RequestParam("pwd")String pwd) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(account + "22222"+pwd);
            //用户输入验证
            if (errors.hasFieldErrors("account") || errors.hasFieldErrors("pwd")) {
               // return new Result(0, "字段验证错误", errors.getAllErrors());
                return new Gson().toJson(new Result(0, "字段验证错误", errors.getAllErrors()));
            }

            //业务逻辑验证
            try {
                //md5加密
                System.out.println(user + "11111");
                user.setPwd(MD5Utils.stringToMD5(user.getPwd()));
                User dbuser = userService.login(user);
                session.setAttribute("loginedUser", dbuser);
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


    @RequestMapping("register")
    public ModelAndView register(@Valid User user, Errors errors, ModelAndView mav) {
        //设置默认（成功）跳转的页面
        //页面跳转行为：1、请求转发（默认） 2、响应重定向
        mav.setViewName("redirect:index");

        //验证用户输入的信息
        if(errors.hasErrors()) {
            mav.addObject("errors",errors.getAllErrors());
            mav.setViewName("register");
            return mav;
        }else {
            try {
                //md5加密
                user.setPwd(MD5Utils.stringToMD5(user.getPwd()));
                userService.register(user);
            } catch (BizException e) {
                e.printStackTrace();
                errors.rejectValue("account", "AccountFailure",e.getMessage());
                mav.addObject("errors",errors.getAllErrors());
                mav.setViewName("register");
            }
        }
        //将用户对象传回页面，实现表单的回填
        mav.addObject("user",user);
        mav.addObject("errors",errors.getFieldErrors());

        return mav;
    }
}

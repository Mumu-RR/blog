package com.yc.user.restapi.controllers;


import com.google.gson.Gson;
import com.yc.blog.Userservice.UserServiceImpl;
import com.yc.blog.dao.impl.UserMapper;
import com.yc.blog.entity.Result;
import com.yc.blog.entity.User;
import com.yc.blog.util.BizException;
import com.yc.blog.util.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
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
    @Resource
    private UserMapper um;

    @RequestMapping(value = "/findAll")
    //  @HystrixCommand(fallbackMethod = "errorCallBack")   //模仿没有这个数据时，服务降级
    public CompletableFuture<String> findAll() {

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
            System.out.println(session+"55555555");
            //用户输入验证
            if (errors.hasFieldErrors("account") || errors.hasFieldErrors("pwd")) {
               // return new Result(0, "字段验证错误", errors.getAllErrors());
                return new Gson().toJson(new Result(0, "字段验证错误", errors.getAllErrors()));
            }

            //业务逻辑验证
            try {
                //md5加密
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
    public int register(@Valid User user, Errors errors, @RequestParam("account")String account, @RequestParam("pwd")String pwd, @RequestParam("name")String name, @RequestParam("email")String email, HttpServletResponse response)throws IOException  {
            //设置默认（成功）跳转的页面
            //页面跳转行为：1、请求转发（默认） 2、响应重定向


            //验证用户输入的信息
            if (errors.hasErrors()) {
                try {
                    response.sendRedirect("http://localhost:8095/register.html");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return 0;
            } else {
                try {
                    //md5加密
                    user.setPwd(MD5Utils.stringToMD5(user.getPwd()));
                    userService.register(user);
                } catch (BizException e) {
                    e.printStackTrace();
                    errors.rejectValue("account", "AccountFailure", e.getMessage());
                    try {
                        response.sendRedirect("http://localhost:8095/register.html");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    return 0;
                }
            }
            try {
                response.sendRedirect("http://localhost:8095/index.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        return 0;
    }


    //找回密码页面
    @GetMapping("forget.html")
    public ModelAndView toforget(ModelAndView mav) {
        mav.setViewName("forget");
        return mav;
    }

    //获取问题
    @GetMapping("getQuestion")
    public CompletableFuture<String> getQuestion(@RequestParam("account")String account) {
        return CompletableFuture.supplyAsync(() -> {
            if (account == null || account.trim().isEmpty()) {
                //return new Result(0, "用户名不能为空！");
                return new Gson().toJson(new Result(0, "用户名不能为空！"));
            }
            User user = um.selectByAccount(account);
            if (user == null) {
                //return new Result(0, "该用户不存在！");
                return new Gson().toJson(new Result(0, "该用户不存在！"));
            }
            //return new Result(1, user.getPwdQuestion());
            return new Gson().toJson(new Result(1, user.getPwdQuestion()));
        });
    }


    //判断答案
    @GetMapping("answer")
    public CompletableFuture<String> answer(@RequestParam("account")String account, @RequestParam("pwdAnswer")String pwdAnswer) {
        return CompletableFuture.supplyAsync(() -> {
            if (account == null || account.trim().isEmpty()) {
                //return new Result(0, "用户名不能为空!");
                return new Gson().toJson(new Result(0, "用户名不能为空!"));
            }
            if (pwdAnswer == null || pwdAnswer.trim().isEmpty()) {
                //return new Result(0, "密码回答不能为空!");
                return new Gson().toJson(new Result(0, "密码回答不能为空!"));
            }
            User user = um.selectByAccountAndPwdAnswer(account, pwdAnswer);
            if (user == null) {
                //return new Result(0, "问题回答不正确!");
                return new Gson().toJson(new Result(0, "问题回答不正确!"));
            }
            //return new Result(1, "问题回答正确!");
            return new Gson().toJson(new Result(1, "问题回答正确!"));
        });
    }

    //重置密码
    @GetMapping("resetPwd")
    public CompletableFuture<String> resetPwd(@RequestParam("account")String account,@RequestParam("pwd") String pwd) {
        return CompletableFuture.supplyAsync(() -> {
            if (account == null || account.trim().isEmpty()) {
                //return new Result(0,"用户名不能为空!");
                return new Gson().toJson(new Result(0, "用户名不能为空!"));
            }
            if (pwd == null || pwd.trim().isEmpty()) {
                //return new Result(0,"密码不能为空!");
                return new Gson().toJson(new Result(0, "密码不能为空!"));
            }
            um.resetPwd(account, MD5Utils.stringToMD5(pwd));
            //return new Result(1,"密码重置成功!");
            return new Gson().toJson(new Result(1, "密码重置成功!"));
        });
    }

    //上传图片
    @PostMapping("uploadImg")
    public Result uploadImg(@RequestParam("img") MultipartFile file) throws IllegalStateException, IOException {
        file.transferTo(new File("e:/cr_img/" + file.getOriginalFilename()));
        //回传 图片的web地址
        return new Result(1,"/imgs/"+file.getOriginalFilename());
    }
}

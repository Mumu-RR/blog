package com.yc.piclib.web.controllers;


import com.google.gson.Gson;
import com.yc.blog.entity.User;
import com.yc.piclib.future.UserFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;


@RestController
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class.getName());

    @Autowired
    private UserFuture userFuture;

    @RequestMapping("/login")
    public CompletableFuture<String> login(@Valid User user, Errors errors, HttpSession session, @RequestParam("account")String account, @RequestParam("pwd")String pwd) {
        //System.out.println("web层"+user+"errors:"+errors);
        session.setAttribute("loginedUser", user);
        System.out.println(session.getAttribute("loginedUser")+"这是session");
        return userFuture.login(user,errors,session,account,pwd);
    }

    @RequestMapping("/doCheck")
    public String doCheck(HttpSession session) throws IOException {
        User user=(User)session.getAttribute("loginedUser");
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("user", user);
        return new Gson().toJson(map);
    }

//    @GetMapping("/toreg")
//    public CompletableFuture<String> toregister( ModelAndView mav){
//        System.out.println("web层"+mav);
//        return userFuture.toregister(mav);
//    }

    @RequestMapping("/register")
    public CompletableFuture<String> register(@Valid User user, Errors errors , @RequestParam("account")String account, @RequestParam("pwd")String pwd, @RequestParam("name")String name, @RequestParam("email")String email){
        System.out.println("web层"+user+"|"+errors+"|"+account+"|"+pwd+"|"+name+"|"+email);
        return userFuture.register(user,errors,account,pwd,name,email);
    }

    //获取问题
    @GetMapping("getQuestion")
    public CompletableFuture<String> getQuestion(@RequestParam("account")String account){
        return  userFuture.getQuestion(account);
    }

    @GetMapping("answer")
    public CompletableFuture<String> answer(@RequestParam("account")String account, @RequestParam("pwdAnswer")String pwdAnswer){
        return  userFuture.answer(account,pwdAnswer);
    }

    @GetMapping("resetPwd")
    public CompletableFuture<String> resetPwd(@RequestParam("account")String account, @RequestParam("pwd")String pwd){
        return  userFuture.resetPwd(account, pwd);
    }

//    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
//    public CompletableFuture<String> findAll(Integer page, Integer pageSize, String description) {
//        return piclibFuture.findPage(page, pageSize, description);
//    }
//
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public CompletableFuture<String> save(@RequestBody PicDomain picDomain) throws Exception {
//        return piclibFuture.create(picDomain);
//    }
//
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    public CompletableFuture<String> delete(@PathVariable Integer id) throws Exception {
//        return piclibFuture.delete(id);
//    }


}

package com.yc.piclib.web.controllers;


import com.yc.blog.entity.User;
import com.yc.piclib.future.UserFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;


@RestController
public class UserController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class.getName());

    @Autowired
    private UserFuture userFuture;

    @RequestMapping("login")
    public CompletableFuture<String> login(@Valid User user, Errors errors, HttpSession session, @RequestParam("account")String account, @RequestParam("pwd")String pwd) {
        System.out.println("web层"+user+"errors:"+errors);
        return userFuture.login(user,errors,session,account,pwd);
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

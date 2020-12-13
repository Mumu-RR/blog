package com.yc.piclib.future;


import com.yc.blog.entity.User;
import com.yc.piclib.Userservice.UserRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.concurrent.CompletableFuture;

// 对外公开的业务层
@Component
public class UserFuture {
    @Autowired
    private UserRestService userRestService;

    @Async
    public CompletableFuture<String> login(User user, Errors errors, HttpSession session, @RequestParam("account")String account, @RequestParam("pwd")String pwd) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("zuulClient"+user+"errors:"+errors);
            return userRestService.login(user,errors,session,account,pwd);
        });
    }


//    @Async
//    public CompletableFuture<String> findPage(Integer page, Integer pageSize,
//                                              String description) {
//        return CompletableFuture.supplyAsync(() -> {
//            return piclibRestService.findAll(page, pageSize, description);
//        });
//    }
//
//
//    @Async
//    public CompletableFuture<String> delete(Integer id) {
//        return CompletableFuture.supplyAsync(() -> {
//            return piclibRestService.delete(id);
//        });
//    }
}

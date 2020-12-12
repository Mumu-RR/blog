package com.yc.piclib.Userservice;

import com.yc.blog.entity.User;
import com.yc.piclib.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

//Hystrix服务层:  调用 PiclibClient,实现断路器功能
@Service
public class UserRestService {
    @Autowired
    private UserClient userClient;


//    @HystrixCommand(fallbackMethod = "findByIdFallback")
    public String login(@RequestParam("user")User user, @RequestParam("errors") Errors errors, @RequestParam("session") HttpSession session, @RequestParam("account")String account, @RequestParam("pwd")String pwd) {
        System.out.println(userClient.getClass());
        System.out.println(user+"3333333333");
        System.out.println(111111);
        return userClient.login(user, errors, session,account,pwd);
    }

//    private String findByIdFallback(Integer id) {
//        Map map = new HashMap();
//        map.put("code", "-1");
//        map.put("msg", "服务异常");
//        return new Gson().toJson(map);
//    }

//    @HystrixCommand(fallbackMethod = "findAllFallback")
//    public String findAll(Integer page, Integer pageSize,
//                          String description) {
//        return piclibClient.findAll(page, pageSize, description);
//    }
//
//    private String findAllFallback(Integer page, Integer pageSize,
//                                   String description) {
//        Map map = new HashMap();
//        map.put("code", "-1");
//        map.put("msg", "服务异常");
//        return new Gson().toJson(map);
//    }
//
//
//    @HystrixCommand(fallbackMethod = "deleteFallback")
//    public String delete(Integer id) {
//        return piclibClient.delete(id);
//    }
//
//    private String deleteFallback(Integer id) {
//        Map map = new HashMap();
//        map.put("code", "-1");
//        map.put("msg", "服务异常，无法删除" + id);
//        return new Gson().toJson(map);
//    }


}


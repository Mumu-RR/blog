package com.yc.blog.Userservice;

import com.yc.blog.dao.impl.UserMapper;
import com.yc.blog.entity.User;
import com.yc.blog.util.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl{

    @Autowired(required = false)
    private UserMapper um;

    public User login(User user) throws BizException {
        User dbuser = um.SelectByAccountAndPwd(user);
        System.out.println(dbuser);
        if(dbuser == null) {
            throw new BizException("用户名或密码错误");
        }
        return dbuser;
    }

    public void register(User user) throws BizException {
        //判断该账号是否已经被注册
        if(um.countByAccount(user) > 0) {
            throw new BizException("该用户已存在");
        }
        //将该用户写入数据库
        um.insert(user);
    }

    public List<User> selectAll(){
       List<User> userList = um.selectAll();
       return userList;
    }


}

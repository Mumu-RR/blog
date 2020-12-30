package com.yc.admin.restapi.service;

import com.yc.blog.dao.impl.AdminMapper;
import com.yc.blog.entity.Admin;
import com.yc.blog.util.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl {

    @Autowired
    private AdminMapper am;


    public Admin login(Admin admin) throws BizException {
        Admin aduser = am.SelectByAccountAndPwd(admin);
        System.out.println(aduser);
        if(aduser == null) {
            throw new BizException("用户名或密码错误");
        }
        return aduser;
    }
}

package com.yc.blog.Userservice;

import com.yc.blog.dao.impl.AdminMapper;
import com.yc.blog.entity.Admin;
import com.yc.blog.util.BizException;
import com.yc.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
public class AdminUserServiceImpl {

    @Autowired
    private AdminMapper am;

    public String select(Admin admin) {
        String account = admin.getName();
        String pwd = admin.getPwd();
        System.out.println(account);
        System.out.println(pwd);

        String result = "1";

        // 将输入的密码使用md5加密

        admin.setPwd(MD5Utils.stringToMD5(admin.getPwd()));
        // 用户不存在
        if ( am.SelectByAccount(account) == null) {
            result = "0";
            return result;
            //  用户存在，但密码输入错误
        }else if(!am.SelectByPwd(account).equals(MD5Utils.stringToMD5(pwd)) ){
            result = "1";
            return result;
            //  登录成功
        }else if(am.SelectByPwd(account).equals(MD5Utils.stringToMD5(pwd))) {
            result = "2";
            return result;
        }
        return result;
    }

    public Admin login(Admin admin) throws BizException {
        Admin aduser = am.SelectByAccountAndPwd(admin);
        System.out.println(aduser);
        if(aduser == null) {
            throw new BizException("用户名或密码错误");
        }
        return aduser;
    }

}

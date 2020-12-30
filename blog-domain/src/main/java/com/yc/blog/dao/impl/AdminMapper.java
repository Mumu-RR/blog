package com.yc.blog.dao.impl;

import com.yc.blog.entity.Admin;

import com.yc.blog.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Mapper  //具体操作某张表的mapper
@Service
public interface AdminMapper {

    @Select("select * from admin where account=#{account} and pwd=#{pwd}")
    Admin SelectByAccountAndPwd(Admin admin);

    @Select("select account from admin where account =#{account}")
    public String SelectByAccount(String account);

    @Select("select pwd from admin where pwd = #{pwd}")
    public String SelectByPwd(String pwd);
}

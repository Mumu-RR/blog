package com.yc.blog.dao.impl;

import com.yc.blog.dao.MisBaseMapper;
import com.yc.blog.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper  //具体操作某张表的mapper
public interface AdminMapper   extends MisBaseMapper<Admin> {

    @Select("select * from admin where account=#{account} and pwd=#{pwd}")
    Admin SelectByAccountAndPwd(Admin admin);

    @Select("select account from admin where account =#{account}")
    String SelectByAccount(String account);

    @Select("select pwd from admin where pwd = #{pwd}")
    String SelectByPwd(String pwd);

    @Select("select * from admin where account = #{account}")
    Admin SelectByAdminAccount(Admin admin);

    @Update("update admin set lastime=#{time},loginNum=loginNum+1,time=now() where account= #{account}")
    void Update(Admin admin);
}

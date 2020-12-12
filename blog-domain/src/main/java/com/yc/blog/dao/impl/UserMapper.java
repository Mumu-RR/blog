package com.yc.blog.dao.impl;

import com.yc.blog.dao.MisBaseMapper;
import com.yc.blog.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

@Mapper  //具体操作某张表的mapper
@Service
public interface UserMapper extends MisBaseMapper<User> {

	@Select("select count(*) from user where account = #{account}")
	int countByAccount(User user);

	@Select("select * from user where account = #{account} and pwd = #{pwd}")
	User SelectByAccountAndPwd(User user);
	
	@Insert("insert into user values(null,#{name},#{account},#{pwd},#{phone},#{email},#{head},#{createtime},#{status},#{type},#{pwdQuestion},#{pwdAnswer})")
	int insert(User user);
	
	@Select("select * from user where account = #{account}")
	@Results(id = "umrm",value = { @Result(column = "pwd_question",property = "pwdQuestion"),
					@Result(column = "pwd_answer",property = "pwdAnswer")})
	User selectByAccount(String account);
	
	@Select("select * from user where account = #{account} and pwd_answer = #{pwdAnswer}")
	@ResultMap("umrm")
	User selectByAccountAndPwdAnswer(@Param("account") String account, @Param("pwdAnswer") String pwdAnswer);
	
	@Update("update user set pwd=#{pwd} where account=#{account}")
	void resetPwd(@Param("account") String account, @Param("pwd") String pwd);
}

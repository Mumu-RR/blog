package com.yc.blog.entity;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data      //  lombok 注解，节省getter, setter
@Table(name = "user")
public class User{
	@Id  // JPA注解,指定此属性为表中的主键
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotEmpty(message="昵称不能为空!")
	@Length(min=4,max=20,message="昵称必须是4~20位")
	private String name;
	@NotEmpty(message="账号不能为空!")
	@Length(min=4,max=20,message="账号必须是4~20位")
	private String account;
	@NotEmpty(message="密码不能为空!")
	@Length(min=4,max=20,message="密码必须是4~20位")
	private String pwd;
	private String phone;
	@NotEmpty(message="邮箱不能为空!")
	@Email(message="邮箱格式错误")
	private String email;
	private String head;
	private Timestamp createtime;
	private String status;
	private String type;
	
	private String pwdQuestion;
	private String pwdAnswer;
	

	
}

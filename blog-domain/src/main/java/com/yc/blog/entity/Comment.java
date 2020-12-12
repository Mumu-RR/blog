package com.yc.blog.entity;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data      //  lombok 注解，节省getter, setter
@Table(name = "comment")
public class Comment{
	@Id  // JPA注解,指定此属性为表中的主键
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer articleid;
	@NotEmpty(message = "评论的内容不能为空！")
	@Length(min=2,max=1000,message="评论的内容必须是2~1000个字符！")
	private String content;
	private Integer createby;
	private Timestamp createtime;
	
	private Article article; // 关联文章   一对一管理
	private User user; 		 // 发表人, 关联 User  一对一管理
	

	
}

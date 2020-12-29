package com.yc.blog.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Data      //  lombok 注解，节省getter, setter
@Table(name = "article")
public class Article{
	@Id  // JPA注解,指定此属性为表中的主键
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String author;
	@NotEmpty
	private String title;
	@NotEmpty
	@Length(min = 50,max = 2000,message = "文章内容长度必须在50~2000个字符之间")
	private String content;
	private String keywords;
	private String description;
	@Min(1)
	private Integer categoryid;
	@NotEmpty
	private String label;
	private String titleimgs;
	private String status;
	private Timestamp createtime;
	private Integer readcnt;
	private Integer agreecnt;
	
	/**
	 * 	所属分类对象
	 */
	private Category category;
	private String categoryName;
	

	
}

package com.yc.blog.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data      //  lombok 注解，节省getter, setter
@Table(name = "category")
public class Category{
	@Id  // JPA注解,指定此属性为表中的主键
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String sort;
	private String introduce;
	private Integer num;

	
	

}

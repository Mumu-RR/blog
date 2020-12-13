package com.yc.blog.dao.impl;

import java.util.List;

import com.yc.blog.dao.MisBaseMapper;
import com.yc.blog.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;



/**
 * 注解(简单sql) + XML(复杂SQL) 配置映射
 * @author 廖彦
 *
 */
@Mapper  //具体操作某张表的mapper
public interface CategoryMapper extends MisBaseMapper<Category> {
	
	@Select("select * from category")
	List<Category> selectAll();

	@Select("select * from category where id = #{id} ")
	Category selectById(int id);
}

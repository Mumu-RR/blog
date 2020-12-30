package com.yc.blog.dao.impl;

import com.yc.blog.dao.MisBaseMapper;
import com.yc.blog.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;



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
	Category selectCategoryById(int id);

	@Select("select c.id,name,count(categoryId) as num from category c inner join article a on c.id=a.categoryId group by c.id;")
	List<Category> selectCategoryAndNum();

	@Insert("insert into category values(null,#{name},null,#{introduce})")
	int addCategory(Category category);

	@Delete("delete from category where id = #{id}")
	void deleteCategoryById(int id);

	@Update("update category set name = #{name},introduce = #{introduce} where id = #{id}")
    void updataCategory(Category category);
}

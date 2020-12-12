package com.yc.blog.dao.impl;

import java.util.List;

import com.yc.blog.dao.MisBaseMapper;

import com.yc.blog.entity.Article;
import org.apache.ibatis.annotations.*;


@Mapper  //具体操作某张表的mapper
public interface ArticleMapper  extends MisBaseMapper<Article> {

	@Select("select * from article order by id desc")
	@Results(
		id="rmAct",
		value={ @Result(  column = "categoryid", property = "category",
					one = @One( select = "com.yc.C81S3Plyblog.dao.CategoryMapper.selectById") ) }
	)
	List<Article> selectNewArticle();

	
	@Select("select * from article where id = #{id}")
	//引用关联查询的配置
	@ResultMap("rmAct")
	Article selectById(int id);

	@Insert("insert into article values(null,#{author},#{title},#{content},null,null,#{categoryid},#{label},null,null,now(),0,0)")
	//获取自增列主键值
	@Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
	int insert(Article article);
}

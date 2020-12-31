package com.yc.blog.dao.impl;

import com.yc.blog.dao.MisBaseMapper;
import com.yc.blog.entity.Article;
import com.yc.blog.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper  //具体操作某张表的mapper
public interface ArticleMapper  extends MisBaseMapper<Article> {

//	@Select("select * from article order by id desc")
//	@Results(
//		id="rmAct",
//		value={ @Result(  column = "categoryid", property = "category",
//					one = @One( select = "com.yc.C81S3Plyblog.dao.CategoryMapper.selectById") ) }
//	)
//	List<Article> selectNewArticle();

	
//	@Select("select * from article where id = #{id}")
//	//引用关联查询的配置
//	@ResultMap("rmAct")
//	Article selectById(int id);

	@Select("select * from article where id = #{id}")
	Article selectById(int id);

	@Insert("insert into article values(null,#{author},#{title},#{content},null,null,#{categoryid},#{label},null,null,now(),0,0)")
	//获取自增列主键值
	@Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
	int insert(Article article);

	@Select("select * from article where categoryId = #{id}")
	List<Article> selectByCategoryId(int id);

	@Select("select * from article order by createTime desc LIMIT 8 OFFSET 1")
	List<Article> selectEight();

	@Select("select a.*,c.name as categoryName from article a,category c where a.categoryId = c.id order by a.createTime desc")
	List<Article> selectAll();

	@Select("select * from article order by readCnt+agreeCnt desc LIMIT 5 OFFSET 1")
	List<Article> hotArticle();

	@Delete("delete from article where id = #{id}")
	void deleteArticleById(int id);

	@Update("update article set title = #{title},content = #{content},label = #{label},categoryId = #{categoryid} where id = #{id}")
	void updataArticle(Article article);
}

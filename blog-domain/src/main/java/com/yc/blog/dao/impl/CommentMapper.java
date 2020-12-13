package com.yc.blog.dao.impl;

import java.util.List;

import com.yc.blog.dao.MisBaseMapper;
import com.yc.blog.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper  //具体操作某张表的mapper
public interface CommentMapper extends MisBaseMapper<Comment> {

	@Insert("insert into comment values(null,#{articleid},#{content},#{createby},now())")
	int insert(Comment comment);
	
	@Select("select * from comment where articleid = #{aid}")
	List<Comment> selectByArticleid(int articleid);
}

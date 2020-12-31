package com.yc.blog.dao.impl;

import com.yc.blog.dao.MisBaseMapper;
import com.yc.blog.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper  //具体操作某张表的mapper
public interface CommentMapper extends MisBaseMapper<Comment> {

	@Insert("insert into comment values(null,#{articleid},#{content},#{createby},now())")
	int insert(Comment comment);
	
	@Select("select c.*,u.name from comment c,user u where articleid = #{aid} and c.createBy = u.id order by createTime desc")
	List<Comment> selectByArticleid(int articleid);

	@Select("select * from comment")
	List<Comment> selectComment();

	@Delete("delete from comment where id = #{id}")
	void deleteCommentById(int id);
}

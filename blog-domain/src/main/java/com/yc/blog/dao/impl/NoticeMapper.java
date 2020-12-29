package com.yc.blog.dao.impl;

import com.yc.blog.dao.MisBaseMapper;
import com.yc.blog.entity.Notice;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper  //具体操作某张表的mapper
public interface NoticeMapper extends MisBaseMapper<Notice> {

    @Insert("insert into notice values(null,#{title},#{content},now(),#{label})")
    int addNewNotice(Notice notice);
}

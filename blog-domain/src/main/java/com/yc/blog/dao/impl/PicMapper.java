package com.yc.blog.dao.impl;

import com.yc.blog.dao.MisBaseMapper;
import com.yc.blog.entity.Pic;
import org.apache.ibatis.annotations.Mapper;

@Mapper  //具体操作某张表的mapper
public interface PicMapper extends MisBaseMapper<Pic> {
}



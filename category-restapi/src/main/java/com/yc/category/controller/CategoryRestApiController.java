package com.yc.category.controller;

import com.google.gson.Gson;
import com.yc.blog.dao.impl.CategoryMapper;
import com.yc.blog.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping
public class CategoryRestApiController {

    private static Logger logger = LoggerFactory.getLogger(CategoryRestApiController.class);

    @Resource
    private CategoryMapper cm;

    @GetMapping("/category")
    public String findCategory(){
        List<Category> list = cm.selectAll();
        return new Gson().toJson(list);

    }
}

package com.yc.category.controller;

import com.google.gson.Gson;
import com.yc.blog.dao.impl.ArticleMapper;
import com.yc.blog.dao.impl.CategoryMapper;
import com.yc.blog.entity.Article;
import com.yc.blog.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping
public class CategoryRestApiController {

    private static Logger logger = LoggerFactory.getLogger(CategoryRestApiController.class);

    @Resource
    private CategoryMapper cm;

    @Resource
    private ArticleMapper am;

    @GetMapping("/category")
    public String findCategory(){
        List<Category> list = cm.selectAll();
        return new Gson().toJson(list);
    }

    @GetMapping("/getArticleByCategoryId")
    public String getArticleByCategoryId(@RequestParam("id")int id){
        List<Article> list = am.selectByCategoryId(id);
        return new Gson().toJson(list);
    }

    @GetMapping("/selectCategoryAndNum")
    public String selectCategoryAndNum(){
        List<Category> list = cm.selectCategoryAndNum();
        return new Gson().toJson(list);
    }

    @GetMapping("/addCategory")
    public void addCategory(@RequestParam("name")String name, @RequestParam("introduce")String introduce, HttpServletResponse response){
        Category category = new Category();
        category.setName(name);
        category.setIntroduce(introduce);
        cm.addCategory(category);
        try {
            response.sendRedirect("http://localhost:8090/category.html");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("deleteCategoryById")
    public void deleteCategoryById(@RequestParam("id")int id){
        cm.deleteCategoryById(id);
    }


}

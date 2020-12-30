package com.yc.controller;

import com.yc.blog.entity.Category;
import com.yc.piclib.future.CategoryFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
public class CategoryController {
    private static Logger logger = LoggerFactory.getLogger(CategoryController.class.getName());

    @Autowired
    private CategoryFuture categoryFuture;

    @GetMapping("/selectCategoryAndNum")
    public CompletableFuture<String> selectCategoryAndNum() {
        return categoryFuture.selectCategoryAndNum();
    }

    @RequestMapping("/addCategory")
    public CompletableFuture<String> addCategory(@RequestParam("name")String name,@RequestParam("introduce")String introduce){
        return categoryFuture.addCategory(name,introduce);
    }

    @GetMapping("/deleteCategoryById")
    public CompletableFuture<String> deleteCategoryById(@RequestParam("id")int id) {
        return categoryFuture.deleteCategoryById(id);
    }

    @GetMapping("/selectCategoryById")
    public CompletableFuture<String> selectCategoryById(@RequestParam("id")int id) {
        return categoryFuture.selectCategoryById(id);
    }

    @RequestMapping("/updateCategory")
    public CompletableFuture<String> updateCategory(@RequestBody Category category) {
        System.out.println("web层："+category);
        int id = category.getId();
        String name = category.getName();
        String introduce = category.getIntroduce();
        return categoryFuture.updateCategory(id,name,introduce);
    }
}

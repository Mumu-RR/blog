package com.yc.controller;

import com.yc.piclib.future.CategoryFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

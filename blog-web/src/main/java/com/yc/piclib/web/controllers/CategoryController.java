package com.yc.piclib.web.controllers;

import com.yc.piclib.future.CategoryFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.concurrent.CompletableFuture;

@RestController
public class CategoryController {

    private static Logger logger = LoggerFactory.getLogger(CategoryController.class.getName());

    @Autowired
    private CategoryFuture categoryFuture;

    @GetMapping("/category")
    public CompletableFuture<String> findCategory() {
        return categoryFuture.findCategory();
    }

    @GetMapping("/getArticleByCategoryId")
    public CompletableFuture<String> getArticleByCategoryId(@RequestParam("id")int id, HttpSession session){
        System.out.println(session.getAttribute("loginedUser")+"这是session");
        return categoryFuture.getArticleByCategoryId(id);
    }
}

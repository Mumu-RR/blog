package com.yc.piclib.future;

import com.yc.piclib.Categoryservice.CategoryRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.CompletableFuture;

// 对外公开的业务层
@Component
public class CategoryFuture {
    @Autowired
    private CategoryRestService categoryRestService;

    @Async
    public CompletableFuture<String> findCategory() {
        return CompletableFuture.supplyAsync(() -> {
            return categoryRestService.findCategory();
        });
    }

    @Async
    public CompletableFuture<String> getArticleByCategoryId(@RequestParam("id")int id){
        return CompletableFuture.supplyAsync(() -> {
            return categoryRestService.getArticleByCategoryId(id);
        });
    }

    @Async
    public CompletableFuture<String>selectCategoryAndNum() {
        return CompletableFuture.supplyAsync(() -> {
            return categoryRestService.selectCategoryAndNum();
        });
    }

    @Async
    public CompletableFuture<String> addCategory(@RequestParam("name")String name,@RequestParam("introduce")String introduce) {
        return CompletableFuture.supplyAsync(() -> {
            return categoryRestService.addCategory(name,introduce);
        });
    }

    @Async
    public CompletableFuture<String> deleteCategoryById(@RequestParam("id")int id) {
        return CompletableFuture.supplyAsync(() -> {
            return categoryRestService.deleteCategoryById(id);
        });
    }
}

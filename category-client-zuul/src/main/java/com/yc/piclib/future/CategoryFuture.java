package com.yc.piclib.future;

import com.yc.piclib.Categoryservice.CategoryRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

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
}

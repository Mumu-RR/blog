package com.yc.piclib.Categoryservice;

import com.yc.piclib.client.CategoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class CategoryRestService {
    @Autowired
    private CategoryClient categoryClient;

    public String findCategory() {
        return categoryClient.findCategory();
    }

    public String getArticleByCategoryId(@RequestParam("id")int id){
        return categoryClient.getArticleByCategoryId(id);
    }

    public String selectCategoryAndNum() {
        return categoryClient.selectCategoryAndNum();
    }

    public String addCategory(@RequestParam("name")String name,@RequestParam("introduce")String introduce) {
        return categoryClient.addCategory(name,introduce);
    }

    public String deleteCategoryById(@RequestParam("id")int id) {
        return categoryClient.deleteCategoryById(id);
    }

    public String selectCategoryById(@RequestParam("id")int id) {
        return categoryClient.selectCategoryById(id);
    }

    public String updateCategory(@RequestParam("id")int id,@RequestParam("name")String name,@RequestParam("introduce")String introduce) {
        return categoryClient.updateCategory(id,name,introduce);
    }
}

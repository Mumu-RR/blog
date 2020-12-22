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
}

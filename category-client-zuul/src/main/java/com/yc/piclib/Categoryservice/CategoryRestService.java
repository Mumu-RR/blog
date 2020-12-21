package com.yc.piclib.Categoryservice;

import com.yc.piclib.client.CategoryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryRestService {
    @Autowired
    private CategoryClient categoryClient;

    public String findCategory() {
        System.out.println(1111111);
        return categoryClient.findCategory();
    }
}

package com.yc.piclib.client;

import com.yc.piclib.config.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

// feign客户端要访问的是  zuul服务 BASE-MICROSERVICE-ZUUL-GATEWAY
@FeignClient(name = "BASE-MICROSERVICE-ZUUL-GATEWAY",
        configuration = FeignClientConfig.class
)  // 配置要按自定义的类FeignClientConfig
@Component
public interface CategoryClient {

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/category-proxy/category")
    String findCategory();

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/category-proxy/getArticleByCategoryId")
    String getArticleByCategoryId(@RequestParam("id")int id);

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/category-proxy/selectCategoryAndNum")
    String selectCategoryAndNum();

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/category-proxy/addCategory")
    String addCategory(@RequestParam("name")String name,@RequestParam("introduce")String introduce);

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/category-proxy/deleteCategoryById")
    String deleteCategoryById(@RequestParam("id")int id);

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/category-proxy/selectCategoryById")
    String selectCategoryById(@RequestParam("id")int id);

    @RequestMapping(method = RequestMethod.GET,value = "/yc-api/category-proxy/updateCategory")
    String updateCategory(@RequestParam("id")int id,@RequestParam("name")String name,@RequestParam("introduce")String introduce);
}

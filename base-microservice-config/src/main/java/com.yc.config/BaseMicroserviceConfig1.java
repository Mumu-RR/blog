package com.yc.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class BaseMicroserviceConfig1 {

    public static void main(String[] args) {

        SpringApplication.run(BaseMicroserviceConfig1.class, args);
        //测试
    }
}


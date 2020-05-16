package com.aaa.lwq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @program: qy-108
 * @author: lwq
 * @create: 2020-05-16 18:32
 * @description:
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ApplicationRun6081 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun6081.class,args);
    }
}

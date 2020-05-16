package com.aaa.lwq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @program: qy-108
 * @author: lwq
 * @create: 2020-05-15 21:36
 * @description:
 **/
@SpringBootApplication
@EnableEurekaServer
public class ApplicationRun7083 {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRun7083.class,args);
    }
}

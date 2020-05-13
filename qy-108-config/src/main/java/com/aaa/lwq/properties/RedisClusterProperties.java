package com.aaa.lwq.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @program: qy-108
 * @author: lwq
 * @create: 2020-05-13 20:36
 * @description:
 *      @PropertySource这个注解的作用是加载某一个properties文件中属性的值
 **/

@Component
@PropertySource("classpath:properties/redis_cluster.properties")
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisClusterProperties {
    private String nodes;
    private Integer maxAttempts;
    private Integer commandTimeout;
}

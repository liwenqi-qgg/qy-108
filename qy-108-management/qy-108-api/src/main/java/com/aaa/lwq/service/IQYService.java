package com.aaa.lwq.service;

import com.aaa.lwq.model.User;
import com.aaa.lwq.vo.TokenVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @program: qy-108
 * @author: lwq
 * @create: 2020-05-16 10:39
 * @description:
 **/
@FeignClient(value = "system-interface")
public interface IQYService {

    @PostMapping("/doLogin")
    TokenVo doLogin(@RequestBody User user);
}

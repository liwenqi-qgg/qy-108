package com.aaa.lwq.controller;


import com.aaa.lwq.model.User;
import com.aaa.lwq.redis.RedisService;
import com.aaa.lwq.service.LoginService;
import com.aaa.lwq.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: qy-108
 * @author: lwq
 * @create: 2020-05-16 10:35
 * @description:
 **/
@RestController
public class LoginController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private LoginService loginService;

    @PostMapping("/doLogin")
    public TokenVo doLogin(@RequestBody User user) {
        return loginService.doLogin(user, redisService);
    }
}

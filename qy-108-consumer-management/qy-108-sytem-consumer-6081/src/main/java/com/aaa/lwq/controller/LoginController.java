package com.aaa.lwq.controller;

import com.aaa.lwq.base.BaseController;
import com.aaa.lwq.base.ResultData;
import com.aaa.lwq.model.User;
import com.aaa.lwq.service.IQYService;
import com.aaa.lwq.vo.TokenVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/5/15 15:55
 * @Description
 **/
@RestController
@Api(value = "登录信息", tags = "用户登录接口")
public class LoginController extends BaseController {

    @Autowired
    private IQYService qyService;

    /**
     * @author Seven Lee
     * @description
     *      执行登录操作
     * @param [user]
     * @date 2020/5/15
     * @return com.aaa.lee.base.ResultData
     * @throws
    **/
    @PostMapping("/doLogin")
    @ApiOperation(value = "登录功能", notes = "用户执行登录功能")
    public ResultData doLogin(User user) {
        TokenVo tokenVo = qyService.doLogin(user);
        if(tokenVo.getIfSuccess()) {
            return super.loginSuccess(tokenVo.getToken());
        }
        return super.loginFailed();
    }

}

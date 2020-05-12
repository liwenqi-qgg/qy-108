package com.aaa.lwq.base;

import static com.aaa.lwq.status.LoginStatus.*;

/**
 * @program: qy-108
 * @author: lwq
 * @create: 2020-05-12 19:32
 * @description:
 *      通用controller，实现统一返回值
 **/
public class BaseController {

    /**
     * @author lwq 
     * @description
     *    登录成功使用系统消息
     * @param: []
     * @date 2020/5/12
     * @return com.aaa.lwq.base.ResultData
     * @throws 
     **/
    protected ResultData loginSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        return resultData;
    }
    /**
     * @author lwq 
     * @description
     *    登录成功，使用自定义消息
     * @param: [msg]
     * @date 2020/5/12
     * @return com.aaa.lwq.base.ResultData
     * @throws 
     **/
    protected ResultData loginSuccess(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /**
     * @author lwq 
     * @description
     *    登录成功，使用系统消息，自定义返回值
     * @param: [data]
     * @date 2020/5/12
     * @return com.aaa.lwq.base.ResultData
     * @throws 
     **/
    protected ResultData loginSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }
    
    /**
     * @author lwq 
     * @description
     *    登录成功，使用自定义消息，自定义返回值
     * @param: [msg, data]
     * @date 2020/5/12
     * @return com.aaa.lwq.base.ResultData
     * @throws 
     **/
    protected ResultData loginSuccess(String msg , Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    /**
     * @author lwq 
     * @description
     *    登录失败，使用自定义消息
     * @param: []
     * @date 2020/5/12
     * @return com.aaa.lwq.base.ResultData
     * @throws 
     **/
    protected ResultData loginFailed() {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(LOGIN_FAILED.getMsg());
        return resultData;
    }
    /**
     * @author lwq
     * @description
     *    登录失败，使用自定义消息
     * @param: [msg]
     * @date 2020/5/12
     * @return com.aaa.lwq.base.ResultData
     * @throws
     **/
    protected ResultData loginFailed(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /**
     * @author lwq
     * @description
     *    登录失败，使用系统消息，自定义返回值
     * @param: [data]
     * @date 2020/5/12
     * @return com.aaa.lwq.base.ResultData
     * @throws
     **/
    protected ResultData loginFailed(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /**
     * @author lwq
     * @description
     *    登录失败，使用自定义消息，自定义返回值
     * @param: [msg, data]
     * @date 2020/5/12
     * @return com.aaa.lwq.base.ResultData
     * @throws
     **/
    protected ResultData loginFailed(String msg , Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

}

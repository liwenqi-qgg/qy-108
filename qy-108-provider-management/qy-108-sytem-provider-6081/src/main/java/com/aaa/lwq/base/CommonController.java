package com.aaa.lwq.base;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @program: qy-108
 * @author: lwq
 * @create: 2020-05-12 19:29
 * @description
 **/
public abstract class CommonController<T> extends BaseController {
    /**
     * @author lwq 
     * @description
     *    钩子函数，在新增之前所执行的内容
     * @param: [map]
     * @date 2020/5/12
     * @return void
     * @throws 
     **/
    protected void beforeAdd(Map map) {
        // TODO 钩子函数，也就是说如果在插入之前你需要执行某些业务的时候，则需要编写内容
    }

    /**
     * @author lwq 
     * @description
     *    钩子函数，在新增之后所执行的内容
     * @param: [map]
     * @date 2020/5/12
     * @return void
     * @throws 
     **/
    protected void afterAdd(Map map) {
        // TODO 钩子函数，也就是说如果在插入之后你需要执行某些业务的时候，则需要编写内容
    }

    public abstract BaseService<T> getBaseService();

    /**
     * @author lwq 
     * @description
     *    新增数据
     * @param: [map]
     * @date 2020/5/12
     * @return com.aaa.lwq.base.ResultData
     * @throws 
     **/
    public ResultData add(@RequestBody Map map) {
        ResultData resultData = new ResultData();
        beforeAdd(map);
        T instance = getBaseService().newInstance(map);
        try {
            Integer insertResult = getBaseService().add(instance);
            if(insertResult > 0) {
                // 说明保存成功
                afterAdd(map);
                return loginSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginFailed();
    }
    /**
     * @author lwq 
     * @description
     *    删除一条数据
     * @param: [map]
     * @date 2020/5/12
     * @return com.aaa.lwq.base.ResultData
     * @throws 
     **/
    public ResultData delete(@RequestBody Map map){
        ResultData resultData = new ResultData();
        T instance = getBaseService().newInstance(map);
        try {
            Integer deleteResult = getBaseService().delete(instance);
            if (deleteResult > 0){
                return loginSuccess();
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
        return loginFailed();        
    }
    // TODO delete, batchDelete, update, getOne, getList, getListByPage(不带条件的分页查询)

    /**
     * @author lwq 
     * @description
     *      防止数据不安全，所以不能直接在controller某个方法中直接接收HttpServletRequest对象
     *      必须要从本地当前线程中获取对象   
     * @param: 
     * @date 2020/5/12
     * @return 
     * @throws 
     **/
    public HttpServletRequest getServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes;
        if(requestAttributes instanceof ServletRequestAttributes) {
            servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * @author lwq 
     * @description
     *    获取当前客户端的session对象(如果不存在，则会重新创建一个)
     * @param: []
     * @date 2020/5/12
     * @return javax.servlet.http.HttpSession
     * @throws 
     **/
    public HttpSession getSession() {
        return getServletRequest().getSession();
    }

    /**
     * @author lwq 
     * @description
     *    获取当前客户端的session对象(如果不存在，则直接返回为null)
     * @param: []
     * @date 2020/5/12
     * @return javax.servlet.http.HttpSession
     * @throws 
     **/
    public HttpSession getExistSession() {
        return getServletRequest().getSession(false);

    }


}

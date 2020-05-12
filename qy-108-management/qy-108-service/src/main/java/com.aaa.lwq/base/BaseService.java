package com.aaa.lwq.base;

import com.aaa.lwq.utils.Map2BeanUtils;
import com.aaa.lwq.utils.SpringContextUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Program: qy-108
 * @Author: lwq
 * @Create: 2020/05/11 21:00
 * @Description:
 *        通用service
 **/
public abstract class BaseService<T> {


    private Class<T> cache = null;

    @Autowired
    private Mapper<T> mapper;

    /**
     * @author lwq 
     * @description
     *    上面的这个Mapper为了保证安全性，必须要是private类型，那么他的子类(UserService)就调用不到
     *    所以需要提供方法给子类用
     * @param: []
     * @date 2020/5/12
     * @return tk.mybatis.mapper.common.Mapper
     * @throws 
     **/
    protected Mapper getMapper() {
        return mapper;
    }

    /**
     * @author lwq 
     * @description
     *    新增功能
     * @param: [t]
     * @date 2020/5/12
     * @return java.lang.Integer
     * @throws 
     **/
    public Integer add(T t) throws Exception {
        return mapper.insertSelective(t);
    }

    /**
     * @author lwq 
     * @description
     *    通过主键删除
     * @param: [t]
     * @date 2020/5/12
     * @return java.lang.Integer
     * @throws 
     **/
    public Integer delete(T t) throws Exception {
        return mapper.deleteByPrimaryKey(t);
    }

    /**
     * @author lwq 
     * @description
     *    通过主键批量删除
     * @param: [ids]
     * @date 2020/5/12
     * @return java.lang.Integer
     * @throws 
     **/
    public Integer delete(List<Object> ids) throws Exception {
        Example example = Example.builder(getTypeArguement()).where(Sqls.custom().andIn("id", ids)).build();
        return mapper.deleteByExample(example);
    }

    /**
     * @author lwq 
     * @description
     *    更新功能
     * @param: [t]
     * @date 2020/5/12
     * @return java.lang.Integer
     * @throws 
     **/
    public Integer update(T t) throws Exception {
        return mapper.updateByPrimaryKeySelective(t);
    }

    /**
     * @author lwq 
     * @description
     *    批量更新
     * @param: [t, ids]
     * @date 2020/5/12
     * @return java.lang.Integer
     * @throws 
     **/
    public Integer batchUpdate(T t, Object[] ids) throws Exception {
        Example example = Example.builder(getTypeArguement()).where(Sqls.custom().andIn("id", Arrays.asList(ids))).build();
        return mapper.updateByExample(t, example);
    }

    /**
     * @author lwq 
     * @description
     *    查询一条数据
     * @param: [t]
     * @date 2020/5/12
     * @return T
     * @throws 
     **/
    public T queryOne(T t) throws Exception {
        return mapper.selectOne(t);
    }

    /**
     * @author lwq 
     * @description
     *    通过指定字段查询一条数据
     * @param: [where, orderByField, fields]
     * @date 2020/5/12
     * @return T
     * @throws 
     **/
    public T queryByField(Sqls where, String orderByField, String... fields) throws Exception {
        return (T) queryByFieldsBase(null, null, where, orderByField, fields).get(0);
    }

    /**
     * @author lwq 
     * @description
     *    条件查询集合
     * @param: [where, orderByField, fields]
     * @date 2020/5/12
     * @return java.util.List<T>
     * @throws 
     **/
    public List<T> queryListByFields(Sqls where, String orderByField, String... fields) throws Exception {
        return queryByFieldsBase(null, null, where, orderByField, fields);
    }

    /**
     * @author lwq 
     * @description
     *    条件查询分页
     * @param: [pageNo, pageSize, where, orderByFileds, fields]
     * @date 2020/5/12
     * @return com.github.pagehelper.PageInfo<T>
     * @throws 
     **/
    public PageInfo<T> queryListByPageAndFields(Integer pageNo, Integer pageSize, Sqls where, String orderByFileds, String... fields) throws Exception {
        return new PageInfo<T>(queryByFieldsBase(pageNo, pageSize, where, orderByFileds, fields));
    }

   /**
    * @author lwq 
    * @description
    *    条件查询
    * @param: [t]
    * @date 2020/5/12
    * @return java.util.List<T>
    * @throws 
    **/
    public List<T> queryList(T t) throws Exception {
        return mapper.select(t);
    }

    /**
     * @author lwq 
     * @description
     *    分页查询
     * @param: [t, pageNo, pageSize]
     * @date 2020/5/12
     * @return com.github.pagehelper.PageInfo<T>
     * @throws 
     **/
    public PageInfo<T> queryListByPage(T t, Integer pageNo, Integer pageSize) throws Exception {
        PageHelper.startPage(pageNo, pageSize);
        List<T> select = mapper.select(t);
        PageInfo<T> pageInfo = new PageInfo<T>(select);
        return pageInfo;
    }

    /**
     * @author lwq 
     * @description
     *    封装条件查询，分页查询以及排序查询的通用方法(多条件查询)
     * @param: [pageNo, pageSize, where, orderByField, field]
     * @date 2020/5/12
     * @return java.util.List<T>
     * @throws 
     **/
    private List<T> queryByFieldsBase(Integer pageNo, Integer pageSize, Sqls where, String orderByField, String... field) {
        Example.Builder builder = null;
        if(null == field || field.length == 0) {
            // 没有条件查询，说明查询的是所有数据
            builder = Example.builder(getTypeArguement());
        } else {
            // 指定某些/某个字段进行查询
            builder = Example.builder(getTypeArguement())
                    .select(field);
        }
        if(null != where) {
            builder = builder.where(where);
        }

        if(null != orderByField) {
            builder = builder.orderByDesc(orderByField);
        }

        Example example = builder.build();
        // pageHelper通用分页插件
        if(null != pageNo && null != pageSize) {
            PageHelper.startPage(pageNo, pageSize);
        }

        List list = getMapper().selectByExample(example);
        return list;
    }

    /**
     * @author lwq 
     * @description
     *    获取子类泛型类型
     * @param: []
     * @date 2020/5/12
     * @return java.lang.Class<T>
     * @throws 
     **/
    private Class<T> getTypeArguement() {
        if(null == cache) {
            cache = (Class<T>) ((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
        }
        return cache;
    }

    /**
     * @author lwq 
     * @description
     *     根据反射获取实例对象
     * @param: [map]
     * @date 2020/5/12
     * @return T
     * @throws 
     **/
    public T newInstance(Map map) {
        return (T) Map2BeanUtils.map2Bean(map, getTypeArguement());
    }

   /**
    * @author lwq 
    * @description
    *    获取spring容器
    * @param: []
    * @date 2020/5/12
    * @return org.springframework.context.ApplicationContext
    * @throws 
    **/
    public ApplicationContext getApplicationContext() {
        return SpringContextUtils.getApplicationContext();
    }
}

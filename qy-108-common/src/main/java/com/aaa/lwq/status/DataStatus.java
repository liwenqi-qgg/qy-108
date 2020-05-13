package com.aaa.lwq.status;

/**
 * @program: qy-108
 * @author: lwq
 * @create: 2020-05-13 11:34
 * @description
 **/
public enum DataStatus {

    GET_SUCCESS("20001","获取数据成功"),
    GET_FAILED("10001","获取数据失败"),
    ADD_SUCCESS("20001","新增数据成功"),
    ADD_FAILED("10001","新增数据失败"),
    UPDATE_SUCCESS("20001","更改数据成功"),
    UPDATE_FAILED("10001","更改数据失败"),
    DELETE_SUCCESS("20001","删除数据成功"),
    DElETE_FAILED("10001","删除数据失败");

    DataStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}


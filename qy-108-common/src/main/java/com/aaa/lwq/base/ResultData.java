package com.aaa.lwq.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: qy-108
 * @author: lwq
 * @create: 2020-05-12 19:32
 * @description:
 **/
@Data
@Accessors(chain = true)
public class ResultData<T> implements Serializable {

    private String code;
    private String msg;
    private String detail;
    private T data;
}

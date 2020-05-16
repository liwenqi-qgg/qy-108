package com.aaa.lwq.utils;

import java.util.UUID;

/**
 * @Company AAA软件教育
 * @Author Seven Lee
 * @Date Create in 2020/5/15 16:33
 * @Description
 **/
public class IDUtils {

    private IDUtils() {

    }

    /**
     * @author Seven Lee
     * @description
     *      获取uuid
     * @param [id]
     * @date 2020/5/15
     * @return java.lang.String
     * @throws
    **/
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}

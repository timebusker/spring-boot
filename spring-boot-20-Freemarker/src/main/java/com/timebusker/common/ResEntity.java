package com.timebusker.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @DESC:ResEntity:数据返回结果集
 * @author:timebusker
 * @date:2018/6/23
 */
public class ResEntity extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 1L;

    public ResEntity() {
        put("code", 0);
    }

    public static ResEntity error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static ResEntity error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }

    public static ResEntity error(int code, String msg) {
        ResEntity r = new ResEntity();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static ResEntity ok(String msg) {
        ResEntity r = new ResEntity();
        r.put("msg", msg);
        return r;
    }

    public static ResEntity ok(Map<String, Object> map) {
        ResEntity r = new ResEntity();
        r.putAll(map);
        return r;
    }

    public static ResEntity ok() {
        return new ResEntity();
    }

    public ResEntity put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}

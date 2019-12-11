package com.paopao.blog.utils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ：paopao
 * @date ：Created in 2019/6/12 10:47
 * @description：返回结果工具类
 */

public class ResultUtil {

    public static final String SUCCESS_RESULT = "S";
    public static final String ERROR_RESULT = "F";
    public static final String MESSAGE = "成功";
    public static final String ERROR = "失败";

    public static Map<String, Object> success() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("RESULT", SUCCESS_RESULT);
        map.put("MESSAGE", MESSAGE);
        return map;
    }

    public static Map<String, Object> success(String message) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("RESULT", SUCCESS_RESULT);
        map.put("MESSAGE", message);
        return map;
    }

    public static Map<String, Object> success(Map paramMap) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("RESULT", SUCCESS_RESULT);
        map.put("MESSAGE", MESSAGE);
        map.putAll(paramMap);
        return map;
    }

    public static Map<String, Object> success(String key, Object value) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("RESULT", SUCCESS_RESULT);
        map.put("MESSAGE", MESSAGE);
        map.put(key, value);
        return map;
    }

    public static Map<String, Object> error() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("RESULT", ERROR_RESULT);
        map.put("MESSAGE", ERROR);
        return map;
    }

    public static Map<String, Object> error(Object message) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("RESULT", ERROR_RESULT);
        map.put("MESSAGE", message);
        return map;
    }

}

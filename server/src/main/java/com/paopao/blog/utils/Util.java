package com.paopao.blog.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：paopao
 * @date ：Created in 2019/6/14 15:50
 * @description：工具类
 */

public class Util {

    public static List<Object> limitPage(List<Object> dataList, int page, int size) {
        //倒序
        java.util.Collections.reverse(dataList);
        List<Object> resultList = new ArrayList<>();
        try {
            for (int j = 0; j < size; j++) {
                dataList.get(page * size + j);
                resultList.add(dataList.get(page * size + j));
            }
        } catch (Exception e) {
        }
        return resultList;
    }

    //spring data jpa 自带的分页分装
    public static PageRequest getPageRequest(Map<String, Object> map) {
        int page = (int) map.get("page");
        int size = (int) map.get("size");
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return PageRequest.of(page, size, sort);
    }

}

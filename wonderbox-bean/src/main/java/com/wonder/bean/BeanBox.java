package com.wonder.bean;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;
import java.util.function.Function;

/**
 * @author Ge Mingjia
 * @date 2023/6/3
 */
public class BeanBox {

    /**
     * Page<T> 类型之间的互相转换（一般为大类转小类Page -> PageVO）
     * @param page mybatis-plus Page类
     * @param converter 自定义转换逻辑
     * @param <T> 原始泛型类型
     * @param <V> 转换后泛型类型
     * @return 转换后的Page泛型类
     */
    public static <T, V> Page<V> toNewPage(Page<T> page, Function<T, V> converter) {
        List<T> records = page.getRecords();
        List<V> voList = records.stream()
                .map(converter)
                .toList();

        Page<V> newPage = new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
        newPage.setRecords(voList);
        return newPage;
    }

}

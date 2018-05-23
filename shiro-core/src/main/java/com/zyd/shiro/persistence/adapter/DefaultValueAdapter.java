package com.zyd.shiro.persistence.adapter;

import com.zyd.shiro.util.ToStringUtil;
import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @since --created on 2018/5/23 at 14:54
 * 空属性值默认值适配器
 */
@AllArgsConstructor
public class DefaultValueAdapter<T> implements AdapteeTarget {

    private T t;

    private int capacity;

    private Map<Class, Object> map;

    @Override
    public String toString() {
        return ToStringUtil.getObjectStringNotEmpty(t, capacity, map);
    }
}

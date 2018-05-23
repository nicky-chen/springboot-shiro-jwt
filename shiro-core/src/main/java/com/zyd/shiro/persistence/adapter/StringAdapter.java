package com.zyd.shiro.persistence.adapter;

import com.zyd.shiro.util.ToStringUtil;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @since --created on 2018/5/23 at 14:33
 * 字符串空适配器
 */
public class StringAdapter<T> implements AdapteeTarget {

    private T t;

    private int capacity;

    public StringAdapter(T t, int capacity) {
        this.t = t;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
       return ToStringUtil.getObjectString(t, capacity);
    }
}

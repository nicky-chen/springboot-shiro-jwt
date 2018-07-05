package com.nicky.shiro.persistence.adapter;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @since --created on 2018/7/5 at 10:17
 */
public class StyleAdapter<T> implements AdapteeTarget {

    private T t;

    private ToStringStyle stringStyle;

    public StyleAdapter(T t, ToStringStyle style) {
        this.t = t;
        this.stringStyle = style;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(t, stringStyle);
    }

}

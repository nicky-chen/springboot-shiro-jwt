package com.zyd.shiro.persistence.adapter;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @since --created on 2018/5/23 at 11:46
 * 适配器适配对象接口
 */
public interface AdapteeTarget {

    @Override
    String toString();

    /**
     * StringBuilder拼接字符串
     * @param capacity 初始化容量
     */
    default String builderToString(int capacity) {
        final StringBuilder builder = new StringBuilder(capacity);
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass(), Object.class);
            PropertyDescriptor[] list = beanInfo.getPropertyDescriptors();
            builder.append(beanInfo.getBeanDescriptor().getName()).append("{");
            for (int i = 0; i < list.length; i++) {
                PropertyDescriptor descriptor = list[i];
                if (i > 0) {
                    builder.append(", ");
                }
                builder.append(descriptor.getName()).append("=").append(descriptor.getReadMethod().invoke(this));
            }
            builder.append("}");
        } catch (IntrospectionException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

}

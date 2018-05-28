package com.nicky.shiro.util;

import org.springframework.util.CollectionUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Objects;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @since --created on 2018/5/23 at 14:41
 */
public final class ToStringUtil {

    /**
     * 字符串属性默认为""
     */
    public static String getObjectString(Object t, int capacity) {
        final StringBuilder builder = new StringBuilder(capacity);
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass(), Object.class);
            PropertyDescriptor[] list = beanInfo.getPropertyDescriptors();
            builder.append(beanInfo.getBeanDescriptor().getName()).append("{");
            commonBuilder(t, builder, list);
            builder.append("}");
        } catch (IntrospectionException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static String getObjectStringValue(Class t, int capacity) {
        final StringBuilder builder = new StringBuilder(capacity);
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(t, Object.class);
            Object obj = t.newInstance();
            PropertyDescriptor[] list = beanInfo.getPropertyDescriptors();
            builder.append(beanInfo.getBeanDescriptor().getName()).append("{");
            commonBuilder(obj, builder, list);
            builder.append("}");
        } catch (IntrospectionException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * 设置属性默认之短
     */
    public static String getObjectStringNotEmpty(Object t, int capacity, Map<Class, Object> map) {

        if (CollectionUtils.isEmpty(map)) {
            return getObjectString(t, capacity);
        }
        final StringBuilder builder = new StringBuilder(capacity);
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(t.getClass(), Object.class);
            PropertyDescriptor[] list = beanInfo.getPropertyDescriptors();
            builder.append(beanInfo.getBeanDescriptor().getName()).append("{");
            defaultBuilder(t, map, builder, list);
            builder.append("}");
        } catch (IntrospectionException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static String getObjectDefaultValue(Class cls, int capacity, Map<Class, Object> map) {

        final StringBuilder builder = new StringBuilder(capacity);
        try {
            Object obj = cls.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(cls, Object.class);
            PropertyDescriptor[] list = beanInfo.getPropertyDescriptors();
            builder.append(beanInfo.getBeanDescriptor().getName()).append("{");
            defaultBuilder(obj, map, builder, list);
            builder.append("}");
        } catch (IntrospectionException | ReflectiveOperationException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    private static void defaultBuilder(Object t, Map<Class, Object> map, StringBuilder builder,
            PropertyDescriptor[] list) throws IllegalAccessException, InvocationTargetException {
        for (int i = 0; i < list.length; i++) {
            PropertyDescriptor descriptor = list[i];
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(descriptor.getName()).append("=");
            Object o = descriptor.getReadMethod().invoke(t);
            Class type = descriptor.getPropertyType();
            if (Objects.isNull(o) && map.containsKey(type)) {
                builder.append(map.get(type));
            } else {
                builder.append(o);
            }

        }
    }

    private static void commonBuilder(Object t, StringBuilder builder, PropertyDescriptor[] list)
            throws IllegalAccessException, InvocationTargetException {
        for (int i = 0; i < list.length; i++) {
            PropertyDescriptor descriptor = list[i];
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(descriptor.getName()).append("=");
            Object o = descriptor.getReadMethod().invoke(t);
            if (descriptor.getPropertyType() == String.class && Objects.isNull(o)) {
                builder.append("''");
            } else {
                builder.append(o);
            }

        }
    }

}

package com.nicky.shiro.persistence.adapter;

import com.nicky.shiro.util.ToStringUtil;
import lombok.AllArgsConstructor;

import java.util.Map;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @since --created on 2018/5/27 at 22:47
 */
public final class AdapterFactory {


    public static  String builderDefaultValueAdapter(Map<Class,Object> map, int capacity, Class cls) {
        return new DefaultValueAdapter(cls, capacity, map).toString() ;
    }

    public static String builderStringValueAdapter(Class cls, int capacity) {
        return new StringAdapter(cls, capacity).toString();
    }

    @AllArgsConstructor
    private static class DefaultValueAdapter implements AdapteeTarget {

        private Class t;

        private int capacity;

        private Map<Class, Object> map;


        @Override
        public String toString() {
            return ToStringUtil.getObjectDefaultValue(t, capacity, map);
        }
    }

    private static class StringAdapter implements AdapteeTarget {

        private Class t;

        private int capacity;

        public StringAdapter(Class t, int capacity) {
            this.t = t;
            this.capacity = capacity;
        }

        @Override
        public String toString() {
            return ToStringUtil.getObjectStringValue(t, capacity);
        }
    }

}

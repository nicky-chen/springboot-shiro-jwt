
package com.nicky.shiro.persistence.beans;

import com.google.common.collect.Maps;
import com.nicky.shiro.framework.object.AbstractDO;
import com.nicky.shiro.persistence.adapter.AdapteeTarget;
import com.nicky.shiro.persistence.adapter.DefaultValueAdapter;
import lombok.*;

import javax.persistence.Transient;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysResources extends AbstractDO {

    private static final long serialVersionUID = 1936021577348624761L;

    /**
     * 资源名
     */
    private String name;

    /**
     * 资源类型
     */
    private String type;

    /**
     * 资源地址
     */
    private String url;

    /**
     * 资源权限
     */
    private String permission;

    /**
     * 父级资源
     */
    private Long parentId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否外部链接
     */
    private Boolean external;

    private Boolean available;

    /**
     * 资源图标
     */
    private String icon;

    @Transient
    private String checked;

    @Transient
    private SysResources parent;

    @Transient
    @Singular
    private List<SysResources> nodes;

    @Override
    public String toString() {
        Map<Class, Object> map = Maps.newHashMapWithExpectedSize(3);
        map.put(Boolean.class, false);
        map.put(Long.class, -1L);
        map.put(List.class, Collections.emptyList());
        AdapteeTarget target = new DefaultValueAdapter<>(this, 1 << 10, map);
        return target.toString();
    }

    public static void main(String[] args) {
        System.out.println(new SysResources());
    }
}

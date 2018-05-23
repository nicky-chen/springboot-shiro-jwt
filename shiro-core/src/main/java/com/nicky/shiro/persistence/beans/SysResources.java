
package com.nicky.shiro.persistence.beans;

import com.nicky.shiro.framework.object.AbstractDO;
import com.nicky.shiro.persistence.adapter.AdapteeTarget;
import com.nicky.shiro.persistence.adapter.DefaultValueAdapter;
import lombok.*;

import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.HashMap;
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
public class SysResources extends AbstractDO implements AdapteeTarget {

    private static final long serialVersionUID = 1936021577348624761L;

    private String name;

    private String type;

    private String url;

    private String permission;

    private Long parentId;

    private Integer sort;

    private Boolean external;

    private Boolean available;

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
        Map<Class, Object> map = new HashMap<>(5);
        map.put(Boolean.class, false);
        map.put(Long.class, -1L);
        map.put(List.class, new ArrayList<>(1));
        AdapteeTarget target = new DefaultValueAdapter<>(this, 1 << 10, map);
        return target.toString();
    }

    public static void main(String[] args) {
        System.out.println(new SysResources());
    }
}

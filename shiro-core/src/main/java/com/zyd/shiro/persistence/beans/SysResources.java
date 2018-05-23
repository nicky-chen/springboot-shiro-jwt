
package com.zyd.shiro.persistence.beans;

import com.zyd.shiro.framework.object.AbstractDO;
import com.zyd.shiro.persistence.adapter.AdapteeTarget;
import lombok.*;

import javax.persistence.Transient;
import java.util.List;

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
        return this.builderToString(1 << 10);
    }
}

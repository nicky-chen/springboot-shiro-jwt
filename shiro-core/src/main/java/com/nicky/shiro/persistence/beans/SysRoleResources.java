package com.nicky.shiro.persistence.beans;

import com.nicky.shiro.framework.object.AbstractDO;
import com.nicky.shiro.persistence.adapter.AdapteeTarget;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleResources extends AbstractDO implements AdapteeTarget {

    private static final long serialVersionUID = -6781285067215766581L;

    private Long roleId;

    private Long resourcesId;

    @Override
    public String toString() {
        return this.builderToString(1 << 6);
    }


}

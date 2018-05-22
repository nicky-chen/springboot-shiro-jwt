package com.zyd.shiro.persistence.beans;

import com.zyd.shiro.framework.object.AbstractDO;
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
public class SysRoleResources extends AbstractDO {

    private static final long serialVersionUID = -6781285067215766581L;

    private Long roleId;

    private Long resourcesId;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SysRoleResources{");
        sb.append("roleId=").append(roleId);
        sb.append(super.toString());
        sb.append(", resourcesId=").append(resourcesId);
        sb.append('}');
        return sb.toString();
    }


}

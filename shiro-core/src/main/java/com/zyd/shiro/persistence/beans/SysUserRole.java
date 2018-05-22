package com.zyd.shiro.persistence.beans;

import com.zyd.shiro.framework.object.AbstractDO;
import lombok.*;

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
@EqualsAndHashCode(callSuper = false, of = {"userId"})
public class SysUserRole extends AbstractDO {

    private static final long serialVersionUID = -1412653526850448092L;

    private Long userId;

    private Long roleId;


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(1 << 7);
        sb.append("SysUserRole{");
        sb.append("userId=").append(userId);
        sb.append(super.toString());
        sb.append(", roleId=").append(roleId);
        sb.append('}');
        return sb.toString();
    }
}

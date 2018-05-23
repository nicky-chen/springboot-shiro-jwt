package com.nicky.shiro.persistence.beans;

import com.nicky.shiro.framework.object.AbstractDO;
import com.nicky.shiro.persistence.adapter.AdapteeTarget;
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
public class SysUserRole extends AbstractDO implements AdapteeTarget {

    private static final long serialVersionUID = -1412653526850448092L;

    private Long userId;

    private Long roleId;


    @Override
    public String toString() {
        return this.builderToString(1 << 7);
    }

    public static void main(String[] args) {
        System.out.println(new SysUserRole());
    }
}

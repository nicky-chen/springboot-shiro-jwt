package com.nicky.shiro.persistence.beans;

import com.nicky.shiro.framework.object.AbstractDO;
import com.nicky.shiro.persistence.adapter.AdapterFactory;
import lombok.*;

import java.util.Date;

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
@EqualsAndHashCode(callSuper = false, of = {"username", "mobile"})
public class SysUser extends AbstractDO {

    private static final long serialVersionUID = 1761095260409301116L;

    private String username;

    private String password;

    private String nickname;

    private String mobile;

    private String email;

    private String qq;

    private Date birthday;

    private Integer gender;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 超级管理员、管理员、普通用户
     */
    private String userType;

    private String regIp;

    private String lastLoginIp;

    private Date lastLoginTime;

    private Integer loginCount;

    private String remark;

    private Integer status;

    @Override
    public String toString() {
        return AdapterFactory.builderStringValueAdapter(SysUser.class, 1 << 10);
    }
}

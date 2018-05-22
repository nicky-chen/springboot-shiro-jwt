package com.zyd.shiro.persistence.beans;

import com.zyd.shiro.framework.object.AbstractDO;
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

    private String avatar;

    private String userType;

    private String regIp;

    private String lastLoginIp;

    private Date lastLoginTime;

    private Integer loginCount;

    private String remark;

    private Integer status;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(1 << 10);
        sb.append("SysUser{");
        sb.append("username=").append(username);
        sb.append(super.toString());
        sb.append(", password=").append(password);
        sb.append(", nickname=").append(nickname);
        sb.append(", mobile=").append(mobile);
        sb.append(", email=").append(email);
        sb.append(", qq=").append(qq);
        sb.append(", birthday=").append(birthday);
        sb.append(", gender=").append(gender);
        sb.append(", avatar=").append(avatar);
        sb.append(", userType=").append(userType);
        sb.append(", regIp=").append(regIp);
        sb.append(", lastLoginIp=").append(lastLoginIp);
        sb.append(", lastLoginTime=").append(lastLoginTime);
        sb.append(", loginCount=").append(loginCount);
        sb.append(", remark=").append(remark);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
}

package com.zyd.shiro.business.service;


import com.zyd.shiro.business.entity.bo.UserRoleBO;
import com.zyd.shiro.framework.object.AbstractService;

/**
 * 用户角色
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public interface SysUserRoleService extends AbstractService<UserRoleBO, Long> {

    /**
     * 添加用户角色
     */
    void addUserRole(Long userId, String roleIds);

    /**
     * 根据用户ID删除用户角色
     */
    void removeByUserId(Long userId);
}

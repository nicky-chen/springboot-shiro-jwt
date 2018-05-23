
package com.zyd.shiro.business.service;

import com.zyd.shiro.business.entity.UserBO;

import java.util.Map;

/**
 * Shiro-权限相关的业务处理
 *
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0

 * @date 2018/4/25 14:37
 * @since 1.0
 */
public interface ShiroService {

    /**
     * 初始化权限
     */
    Map<String, String> loadFilterChainDefinitions();

    /**
     * 重新加载权限
     */
    void updatePermission();

    /**
     * 重新加载用户权限
     *
     * @param user
     */
    void reloadAuthorizingByUserId(UserBO user);

    /**
     * 重新加载所有拥有roleId角色的用户的权限
     *
     * @param roleId
     */
    void reloadAuthorizingByRoleId(Long roleId);

}

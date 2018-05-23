
package com.nicky.shiro.business.service;

import com.nicky.shiro.business.entity.bo.UserBO;

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
     */
    void reloadAuthorizingByUserId(UserBO user);

    /**
     * 重新加载所有拥有roleId角色的用户的权限
     */
    void reloadAuthorizingByRoleId(Long roleId);

}

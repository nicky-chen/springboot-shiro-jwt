package com.zyd.shiro.business.service;


import com.zyd.shiro.business.entity.bo.RoleResourcesBO;
import com.zyd.shiro.framework.object.AbstractService;

/**
 * 角色资源
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public interface SysRoleResourcesService extends AbstractService<RoleResourcesBO, Long> {

    /**
     * 添加角色资源
     */
    void addRoleResources(Long roleId, String resourcesId);

    /**
     * 通过角色id批量删除
     */
    void removeByRoleId(Long roleId);
}

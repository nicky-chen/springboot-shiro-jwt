package com.zyd.shiro.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.shiro.persistence.beans.SysRoleResources;

import java.util.Date;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class RoleResourcesBO {

    private SysRoleResources sysRoleResources;

    public RoleResourcesBO() {
        this.sysRoleResources = new SysRoleResources();
    }

    public RoleResourcesBO(SysRoleResources sysRoleResources) {
        this.sysRoleResources = sysRoleResources;
    }

    @JsonIgnore
    public SysRoleResources getSysRoleResources() {
        return this.sysRoleResources;
    }

    public Long getRoleId() {
        return this.sysRoleResources.getRoleId();
    }

    public void setRoleId(Long roleId) {
        this.sysRoleResources.setRoleId(roleId);
    }

    public Long getResourcesId() {
        return this.sysRoleResources.getResourcesId();
    }

    public void setResourcesId(Long resourcesId) {
        this.sysRoleResources.setResourcesId(resourcesId);
    }

    public Date getCreateTime() {
        return this.sysRoleResources.getCreateTime();
    }

    public void setCreateTime(Date regTime) {
        this.sysRoleResources.setCreateTime(regTime);
    }

    public Date getUpdateTime() {
        return this.sysRoleResources.getUpdateTime();
    }

    public void setUpdateTime(Date updateTime) {
        this.sysRoleResources.setUpdateTime(updateTime);
    }

}

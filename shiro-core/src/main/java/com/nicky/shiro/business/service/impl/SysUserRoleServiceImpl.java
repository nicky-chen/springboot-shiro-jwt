package com.nicky.shiro.business.service.impl;

import com.nicky.shiro.business.entity.bo.UserRoleBO;
import com.nicky.shiro.business.service.SysUserRoleService;
import com.nicky.shiro.persistence.beans.SysUserRole;
import com.nicky.shiro.persistence.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户角色
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    private SysUserRoleMapper resourceMapper;

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     */
    @Override
    public UserRoleBO insert(UserRoleBO entity) {
        Assert.notNull(entity, "UserRole不可为空！");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        resourceMapper.insert(entity.getSysUserRole());
        return entity;
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     */
    @Override
    public void insertList(List<UserRoleBO> entities) {
        Assert.notNull(entities, "entities不可为空！");
        List<SysUserRole> sysUserRole = new ArrayList<>();
        for (UserRoleBO UserRole : entities) {
            UserRole.setUpdateTime(new Date());
            UserRole.setCreateTime(new Date());
            sysUserRole.add(UserRole.getSysUserRole());
        }
        resourceMapper.insertList(sysUserRole);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     */
    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return resourceMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     */
    @Override
    public boolean update(UserRoleBO entity) {
        Assert.notNull(entity, "UserRole不可为空！");
        entity.setUpdateTime(new Date());
        return resourceMapper.updateByPrimaryKey(entity.getSysUserRole()) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     */
    @Override
    public boolean updateSelective(UserRoleBO entity) {
        Assert.notNull(entity, "UserRole不可为空！");
        entity.setUpdateTime(new Date());
        return resourceMapper.updateByPrimaryKeySelective(entity.getSysUserRole()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     */
    @Override
    public UserRoleBO getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysUserRole sysUserRole = resourceMapper.selectByPrimaryKey(primaryKey);
        return null == sysUserRole ? null : new UserRoleBO(sysUserRole);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     */
    @Override
    public UserRoleBO getOneByEntity(UserRoleBO entity) {
        Assert.notNull(entity, "User不可为空！");
        SysUserRole sysUserRole = resourceMapper.selectOne(entity.getSysUserRole());
        return null == sysUserRole ? null : new UserRoleBO(sysUserRole);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     */
    @Override
    public List<UserRoleBO> listAll() {
        List<SysUserRole> sysUserRole = resourceMapper.selectAll();
        return getUserRole(sysUserRole);
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     */
    @Override
    public List<UserRoleBO> listByEntity(UserRoleBO entity) {
        Assert.notNull(entity, "UserRole不可为空！");
        List<SysUserRole> sysUserRole = resourceMapper.select(entity.getSysUserRole());
        return getUserRole(sysUserRole);
    }

    private List<UserRoleBO> getUserRole(List<SysUserRole> sysUserRole) {
        if (CollectionUtils.isEmpty(sysUserRole)) {
            return null;
        }
        List<UserRoleBO> UserRole = new ArrayList<>();
        for (SysUserRole r : sysUserRole) {
            UserRole.add(new UserRoleBO(r));
        }
        return UserRole;
    }

    /**
     * 添加用户角色
     */
    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            readOnly = false,
            rollbackFor = { Exception.class })
    public void addUserRole(Long userId, String roleIds) {
        //删除
        removeByUserId(userId);
        //添加
        String[] roleids = roleIds.split(",");
        UserRoleBO u = null;
        List<UserRoleBO> roles = new ArrayList<>();
        for (String roleId : roleids) {
            u = new UserRoleBO();
            u.setUserId(userId);
            u.setRoleId(Long.parseLong(roleId));
            roles.add(u);
        }
        insertList(roles);
    }

    /**
     * 根据用户ID删除用户角色
     */
    @Override
    @Transactional(
            propagation = Propagation.REQUIRED,
            readOnly = false,
            rollbackFor = { Exception.class },
            timeout = 10)
    public void removeByUserId(Long userId) {
        Example example = new Example(SysUserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        resourceMapper.deleteByExample(example);
    }
}

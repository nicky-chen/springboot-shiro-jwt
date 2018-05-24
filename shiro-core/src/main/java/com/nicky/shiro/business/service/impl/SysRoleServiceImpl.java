package com.nicky.shiro.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nicky.shiro.business.entity.bo.RoleBO;
import com.nicky.shiro.business.service.SysRoleService;
import com.nicky.shiro.business.vo.RoleConditionVO;
import com.nicky.shiro.persistence.beans.SysRole;
import com.nicky.shiro.persistence.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * 获取ztree使用的角色列表
     */
    @Override
    public List<Map<String, Object>> queryRoleListWithSelected(Integer userId) {
        List<SysRole> sysRole = roleMapper.queryRoleListWithSelected(userId);
        if (CollectionUtils.isEmpty(sysRole)) {
            return new ArrayList<>(1);
        }
        List<Map<String, Object>> mapList = new ArrayList<>();
        Map<String, Object> map;
        for (SysRole role : sysRole) {
            map = new HashMap<>(3);
            map.put("id", role.getId());
            map.put("pId", 0);
            map.put("checked", role.getSelected() != null && role.getSelected() == 1);
            map.put("name", role.getDescription());
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<RoleBO> findPageBreakByCondition(RoleConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysRole> sysRoles = roleMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysRoles)) {
            return null;
        }
        List<RoleBO> roles = new ArrayList<>();
        for (SysRole r : sysRoles) {
            roles.add(new RoleBO(r));
        }
        PageInfo bean = new PageInfo<>(sysRoles);
        bean.setList(roles);
        return bean;
    }

    /**
     * 获取用户的角色
     */
    @Override
    public List<RoleBO> listRolesByUserId(Long userId) {
        List<SysRole> sysRoles = roleMapper.listRolesByUserId(userId);
        if (CollectionUtils.isEmpty(sysRoles)) {
            return new ArrayList<>(1);
        }
        List<RoleBO> roles = new ArrayList<>();
        for (SysRole r : sysRoles) {
            roles.add(new RoleBO(r));
        }
        return roles;
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     */
    @Override
    public RoleBO insert(RoleBO entity) {
        Assert.notNull(entity, "Role不可为空！");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        roleMapper.insert(entity.getSysRole());
        return entity;
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     */
    @Override
    public void insertList(List<RoleBO> entities) {
        Assert.notNull(entities, "entities不可为空！");
        List<SysRole> sysRole = new ArrayList<>();
        for (RoleBO role : entities) {
            role.setUpdateTime(new Date());
            role.setCreateTime(new Date());
            sysRole.add(role.getSysRole());
        }
        roleMapper.insertList(sysRole);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     */
    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return roleMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     */
    @Override
    public boolean update(RoleBO entity) {
        Assert.notNull(entity, "Role不可为空！");
        entity.setUpdateTime(new Date());
        return roleMapper.updateByPrimaryKey(entity.getSysRole()) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     */
    @Override
    public boolean updateSelective(RoleBO entity) {
        Assert.notNull(entity, "Role不可为空！");
        entity.setUpdateTime(new Date());
        return roleMapper.updateByPrimaryKeySelective(entity.getSysRole()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     */
    @Override
    public RoleBO getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysRole sysRole = roleMapper.selectByPrimaryKey(primaryKey);
        return null == sysRole ? null : new RoleBO(sysRole);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     */
    @Override
    public RoleBO getOneByEntity(RoleBO entity) {
        Assert.notNull(entity, "User不可为空！");
        SysRole sysRole = roleMapper.selectOne(entity.getSysRole());
        return null == sysRole ? null : new RoleBO(sysRole);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     */
    @Override
    public List<RoleBO> listAll() {
        List<SysRole> sysRole = roleMapper.selectAll();
        return getRole(sysRole);
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     */
    @Override
    public List<RoleBO> listByEntity(RoleBO entity) {
        Assert.notNull(entity, "Role不可为空！");
        List<SysRole> sysRole = roleMapper.select(entity.getSysRole());
        return getRole(sysRole);
    }

    private List<RoleBO> getRole(List<SysRole> sysRole) {
        if (CollectionUtils.isEmpty(sysRole)) {
            return new ArrayList<>(1);
        }
        return sysRole.stream().map(RoleBO::new).collect(Collectors.toList());
    }
}

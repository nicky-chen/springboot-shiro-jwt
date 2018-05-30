package com.nicky.shiro.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nicky.shiro.business.entity.bo.UserBO;
import com.nicky.shiro.business.enums.UserStatusEnum;
import com.nicky.shiro.business.service.SysUserService;
import com.nicky.shiro.business.vo.UserConditionVO;
import com.nicky.shiro.framework.exception.CommonException;
import com.nicky.shiro.framework.holder.RequestHolder;
import com.nicky.shiro.persistence.beans.SysUser;
import com.nicky.shiro.persistence.mapper.SysUserMapper;
import com.nicky.shiro.util.IpUtil;
import com.nicky.shiro.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户
 * @author nicky_chin [shuilianpiying@163.com]
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserBO insert(UserBO user) {
        Assert.notNull(user, "UserBO不可为空！");
        user.setUpdateTime(new Date());
        user.setCreateTime(new Date());
        user.setRegIp(IpUtil.getRealIp(RequestHolder.getRequest()));
        user.setStatus(UserStatusEnum.NORMAL.getCode());
        sysUserMapper.insertSelective(user.getSysUser());
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertList(List<UserBO> users) {
        Assert.notNull(users, "UserBOs不可为空！");
        List<SysUser> sysUsers = new ArrayList<>();
        String regIp = IpUtil.getRealIp(RequestHolder.getRequest());
        for (UserBO user : users) {
            user.setUpdateTime(new Date());
            user.setCreateTime(new Date());
            user.setRegIp(regIp);
            sysUsers.add(user.getSysUser());
        }
        sysUserMapper.insertList(sysUsers);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {
        return sysUserMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(UserBO user) {
        Assert.notNull(user, "User不可为空！");
        user.setUpdateTime(new Date());
        if (!StringUtils.isEmpty(user.getPassword())) {
            try {
                user.setPassword(PasswordUtil.encrypt(user.getPassword(), user.getUsername()));
            } catch (Exception e) {
                throw new CommonException("密码加密失败");
            }
        }
        return sysUserMapper.updateByPrimaryKey(user.getSysUser()) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(UserBO user) {
        Assert.notNull(user, "User不可为空！");
        user.setUpdateTime(new Date());
        if (!StringUtils.isEmpty(user.getPassword())) {
            try {
                user.setPassword(PasswordUtil.encrypt(user.getPassword(), user.getUsername()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new CommonException("密码加密失败");
            }
        } else {
            user.setPassword(null);
        }
        return sysUserMapper.updateByPrimaryKeySelective(user.getSysUser()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     */

    @Override
    public UserBO getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(primaryKey);
        return null == sysUser ? null : new UserBO(sysUser);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     */
    @Override
    public UserBO getOneByEntity(UserBO entity) {
        Assert.notNull(entity, "User不可为空！");
        SysUser sysUser = sysUserMapper.selectOne(entity.getSysUser());
        return null == sysUser ? null : new UserBO(sysUser);
    }

    @Override
    public List<UserBO> listAll() {
        List<SysUser> sysUsers = sysUserMapper.selectAll();

        if (CollectionUtils.isEmpty(sysUsers)) {
            return Collections.emptyList();
        }
        List<UserBO> users = new ArrayList<>();
        for (SysUser sysUser : sysUsers) {
            users.add(new UserBO(sysUser));
        }
        return users;
    }

    @Override
    public List<UserBO> listByEntity(UserBO user) {
        Assert.notNull(user, "User不可为空！");
        List<SysUser> sysUsers = sysUserMapper.select(user.getSysUser());
        if (CollectionUtils.isEmpty(sysUsers)) {
            return Collections.emptyList();
        }
        List<UserBO> users = new ArrayList<>();
        for (SysUser su : sysUsers) {
            users.add(new UserBO(su));
        }
        return users;
    }

    /**
     * 分页查询
     */
    @Override
    public PageInfo<UserBO> findPageBreakByCondition(UserConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysUser> sysUsers = sysUserMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysUsers)) {
            return null;
        }
        List<UserBO> users = new ArrayList<>();
        for (SysUser su : sysUsers) {
            users.add(new UserBO(su));
        }
        PageInfo bean = new PageInfo<>(sysUsers);
        bean.setList(users);
        return bean;
    }

    /**
     * 更新用户最后一次登录的状态信息
     */
    @Override
    public UserBO updateUserLastLoginInfo(UserBO user) {
        if (user != null) {
            user.setLoginCount(user.getLoginCount() + 1);
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(IpUtil.getRealIp(RequestHolder.getRequest()));
            user.setPassword(null);
            this.updateSelective(user);
        }
        return user;
    }

    /**
     * 根据用户名查找
     */
    @Override
    public UserBO getByUserName(String userName) {
        UserBO user = new UserBO(userName, null);
        return getOneByEntity(user);
    }

    /**
     * 通过角色Id获取用户列表
     */
    @Override
    public List<UserBO> listByRoleId(Long roleId) {
        List<SysUser> sysUsers = sysUserMapper.listByRoleId(roleId);
        if (CollectionUtils.isEmpty(sysUsers)) {
            return Collections.emptyList();
        }
        return sysUsers.stream().map(UserBO::new).collect(Collectors.toList());
    }

}

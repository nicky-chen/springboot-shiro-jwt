package com.nicky.shiro.business.service;

import com.github.pagehelper.PageInfo;
import com.nicky.shiro.business.entity.bo.UserBO;
import com.nicky.shiro.business.vo.UserConditionVO;
import com.nicky.shiro.framework.object.AbstractService;

import java.util.List;

/**
 * 用户
 * @author nicky_chin [shuilianpiying@163.com]
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public interface SysUserService extends AbstractService<UserBO, Long> {

    /**
     * 分页查询
     */
    PageInfo<UserBO> findPageBreakByCondition(UserConditionVO vo);

    /**
     * 更新用户最后一次登录的状态信息
     */
    UserBO updateUserLastLoginInfo(UserBO user);

    /**
     * 根据用户名查找
     */
    UserBO getByUserName(String userName);

    /**
     * 通过角色Id获取用户列表
     */
    List<UserBO> listByRoleId(Long roleId);

}

package com.nicky.shiro.business.service;


import com.github.pagehelper.PageInfo;
import com.nicky.shiro.business.entity.bo.RoleBO;
import com.nicky.shiro.business.vo.RoleConditionVO;
import com.nicky.shiro.framework.object.AbstractService;

import java.util.List;
import java.util.Map;

/**
 * 角色
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public interface SysRoleService extends AbstractService<RoleBO, Long> {

    /**
     * 获取ztree使用的角色列表
     */
    List<Map<String, Object>> queryRoleListWithSelected(Integer uid);

    /**
     * 分页查询
     */
    PageInfo<RoleBO> findPageBreakByCondition(RoleConditionVO vo);

    /**
     * 获取用户的角色
     */
    List<RoleBO> listRolesByUserId(Long userId);
}

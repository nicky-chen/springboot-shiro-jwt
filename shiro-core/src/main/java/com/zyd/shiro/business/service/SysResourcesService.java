package com.zyd.shiro.business.service;

import com.github.pagehelper.PageInfo;
import com.zyd.shiro.business.entity.bo.ResourcesBO;
import com.zyd.shiro.business.vo.ResourceConditionVO;
import com.zyd.shiro.framework.object.AbstractService;

import java.util.List;
import java.util.Map;

/**
 * 系统资源
 *
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0

 * @date 2018/4/16 16:26
 * @since 1.0
 */
public interface SysResourcesService extends AbstractService<ResourcesBO, Long> {

    /**
     * 分页查询
     */
    PageInfo<ResourcesBO> findPageBreakByCondition(ResourceConditionVO vo);

    /**
     * 获取用户的资源列表
     */
    List<ResourcesBO> listUserResources(Map<String, Object> map);

    /**
     * 获取ztree使用的资源列表
     */
    List<Map<String, Object>> queryResourcesListWithSelected(Long rid);

    /**
     * 获取资源的url和permission
     */
    List<ResourcesBO> listUrlAndPermission();

    /**
     * 获取所有可用的菜单资源
     */
    List<ResourcesBO> listAllAvailableMenu();

    /**
     * 获取父级资源下所有menu资源
     */
    List<Map<String, Object>> listChildMenuByPid(Long pid);

    /**
     * 获取用户关联的所有资源
     */
    List<ResourcesBO> listByUserId(Long userId);
}

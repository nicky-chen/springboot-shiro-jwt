package com.zyd.shiro.persistence.mapper;

import com.zyd.shiro.business.vo.ResourceConditionVO;
import com.zyd.shiro.persistence.beans.SysResources;
import com.zyd.shiro.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Repository
public interface SysResourceMapper extends BaseMapper<SysResources> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    List<SysResources> findPageBreakByCondition(ResourceConditionVO vo);

    List<SysResources> listUserResources(Map<String, Object> map);

    List<SysResources> queryResourcesListWithSelected(Long rid);

    List<SysResources> listUrlAndPermission();

    List<SysResources> listAllAvailableMenu();

    List<SysResources> listMenuResourceByPid(Long pid);

    List<SysResources> listByUserId(Long userId);
}

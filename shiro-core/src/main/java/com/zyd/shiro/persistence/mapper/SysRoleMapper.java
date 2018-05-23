
package com.zyd.shiro.persistence.mapper;

import com.zyd.shiro.business.vo.RoleConditionVO;
import com.zyd.shiro.persistence.beans.SysRole;
import com.zyd.shiro.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    List<SysRole> findPageBreakByCondition(RoleConditionVO vo);

    List<SysRole> queryRoleListWithSelected(Integer userId);

    List<SysRole> listRolesByUserId(Long userId);
}

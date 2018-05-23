
package com.zyd.shiro.persistence.mapper;

import com.zyd.shiro.business.vo.UserConditionVO;
import com.zyd.shiro.persistence.beans.SysUser;
import com.zyd.shiro.plugin.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Repository("sysUserMapper")
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> findPageBreakByCondition(UserConditionVO vo);

    List<SysUser> listByRoleId(Long roleId);

}

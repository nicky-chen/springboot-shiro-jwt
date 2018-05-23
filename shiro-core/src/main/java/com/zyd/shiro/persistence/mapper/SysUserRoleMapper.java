
package com.zyd.shiro.persistence.mapper;

import com.zyd.shiro.persistence.beans.SysUserRole;
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
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    List<Integer> findUserIdByRoleId(Integer roleId);
}

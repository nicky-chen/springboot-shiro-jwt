
package com.zyd.shiro.plugin;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * 公有Mapper
 *
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0

 * @date 2018/4/16 16:26
 * @since 1.0
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
    // 特别注意，该接口不能被扫描到，否则会出错
}

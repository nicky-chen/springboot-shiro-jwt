package com.zyd.shiro.framework.config;

import org.springframework.stereotype.Component;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Component
@tk.mybatis.spring.annotation.MapperScan("com.zyd.shiro.persistence.mapper")
public class MybatisConfig {
}

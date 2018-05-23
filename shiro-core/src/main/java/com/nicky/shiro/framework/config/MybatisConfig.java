package com.nicky.shiro.framework.config;

import org.springframework.stereotype.Component;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Component
@tk.mybatis.spring.annotation.MapperScan(basePackages = {"com.nicky.shiro.persistence.mapper"})
public class MybatisConfig {
}

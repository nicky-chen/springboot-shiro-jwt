
package com.zyd.shiro.framework.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * redis属性配置文件
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis")
@Getter
@Setter
@Order(-1)
public class RedisProperties {
    private String host;
    private Integer port;
    private String password;
    private Integer timeout;

}

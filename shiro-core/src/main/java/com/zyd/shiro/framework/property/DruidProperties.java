
package com.zyd.shiro.framework.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * druid属性
 *
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/5/17 11:13
 * @since 1.0
 */
@Configuration
@ConfigurationProperties(prefix = "nicky.druid")
@Getter
@Setter
@Order(-1)
public class DruidProperties {

    private String username;

    private String password;

    private String servletPath = "/druid/*";

    private Boolean resetEnable = false;

    private List<String> allowIps;

    private List<String> denyIps;
}

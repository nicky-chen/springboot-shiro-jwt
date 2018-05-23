package com.nicky.shiro.framework.config;

import com.jagregory.shiro.freemarker.ShiroTags;
import com.nicky.shiro.framework.tag.CustomTagDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * freemarker配置类
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Configuration
public class FreeMarkerConfig {

    @Autowired
    protected freemarker.template.Configuration configuration;
    @Autowired
    protected CustomTagDirective customTagDirective;

    /**
     * 添加自定义标签
     */
    @PostConstruct
    public void setSharedVariable() {
        configuration.setSharedVariable("nickyTag", customTagDirective);
        //shiro标签
        configuration.setSharedVariable("shiro", new ShiroTags());
    }
}

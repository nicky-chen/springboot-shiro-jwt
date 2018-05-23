package com.zyd.shiro.framework.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 自定义的异常页面配置
 *
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0

 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Component
public class ErrorPagesConfig {

    /**
     * 自定义异常处理路径
     */
    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return configurableEmbeddedServletContainer -> {
            configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400"));
            configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.UNAUTHORIZED, "/error/401"));
            configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/error/403"));
            configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
            configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
            configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(Throwable.class, "/error/500"));
        };
    }
}

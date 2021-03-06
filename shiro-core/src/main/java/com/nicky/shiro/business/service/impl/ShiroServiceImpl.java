package com.nicky.shiro.business.service.impl;

import com.google.common.collect.Maps;
import com.nicky.shiro.business.entity.bo.ResourcesBO;
import com.nicky.shiro.business.entity.bo.UserBO;
import com.nicky.shiro.business.service.ShiroService;
import com.nicky.shiro.business.service.SysResourcesService;
import com.nicky.shiro.business.service.SysUserService;
import com.nicky.shiro.business.shiro.realm.ShiroRealm;
import com.nicky.shiro.framework.holder.SpringContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Shiro-权限相关的业务处理
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/25 14:37
 * @since 1.0
 */
@Service
@Slf4j
public class ShiroServiceImpl implements ShiroService {

    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock(false);


    @Autowired
    private SysResourcesService resourcesService;

    @Autowired
    private SysUserService userService;

    /**
     * 初始化权限
     */
    @Override
    public Map<String, String> loadFilterChainDefinitions() {
        /*
            配置访问权限
            - anon:所有url都可以匿名访问
            - authc: 需要认证才能进行访问
            - user:配置记住我或认证通过可以访问  例如 user:delete  user:edit
         */
        Map<String, String> filterChainDefinitionMap = Maps.newLinkedHashMap();
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/passport/logout", "logout");
        filterChainDefinitionMap.put("/passport/login", "anon");
        filterChainDefinitionMap.put("/passport/signin", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/error", "anon");
        filterChainDefinitionMap.put("/assets/**", "anon");
        // 加载数据库中配置的资源权限列表
        List<ResourcesBO> resourcesList = resourcesService.listUrlAndPermission();
        resourcesList.stream().filter(resources ->
                !StringUtils.isEmpty(resources.getUrl()) && !StringUtils.isEmpty(resources.getPermission()))
                .forEach(resources -> {
            String permission = "perms[" + resources.getPermission() + "]";
            filterChainDefinitionMap.put(resources.getUrl(), permission);
        });
        filterChainDefinitionMap.put("/**", "authc"); //- authc: 需要认证才能进行访问
        return filterChainDefinitionMap;
    }

    /**
     * 重新加载权限
     */
    @Override
    public void updatePermission() {
        ShiroFilterFactoryBean shirFilter = SpringContextHolder.getBean(ShiroFilterFactoryBean.class);
        lock.readLock().lock();
        try{
            AbstractShiroFilter shiroFilter;
            try {
                shiroFilter = (AbstractShiroFilter) shirFilter.getObject();
            } catch (Exception e) {
                throw new RuntimeException("get ShiroFilter from shiroFilterFactoryBean error!");
            }

            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();

            // 清空老的权限控制
            manager.getFilterChains().clear();

            shirFilter.getFilterChainDefinitionMap().clear();
            shirFilter.setFilterChainDefinitionMap(loadFilterChainDefinitions());
            // 重新构建生成
            Map<String, String> chains = shirFilter.getFilterChainDefinitionMap();
            chains.forEach((url, value) -> {
                String chainDefinition = value.trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            });
        }finally{
            lock.readLock().unlock();
        }
    }

    /**
     * 重新加载用户权限
     */
    @Override
    public void reloadAuthorizingByUserId(UserBO user) {
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        ShiroRealm shiroRealm = (ShiroRealm) rsm.getRealms().iterator().next();
        Subject subject = SecurityUtils.getSubject();
        String realmName = subject.getPrincipals().getRealmNames().iterator().next();
        SimplePrincipalCollection principals = new SimplePrincipalCollection(user.getId(), realmName);
        subject.runAs(principals);
        shiroRealm.getAuthorizationCache().remove(subject.getPrincipals());
        subject.releaseRunAs();

        log.info("用户[{}]的权限更新成功！！", user.getUsername());

    }

    /**
     * 重新加载所有拥有roleId角色的用户的权限
     */
    @Override
    public void reloadAuthorizingByRoleId(Long roleId) {
        List<UserBO> userList = userService.listByRoleId(roleId);
        if (CollectionUtils.isEmpty(userList)) {
            return;
        }
        for (UserBO user : userList) {
            reloadAuthorizingByUserId(user);
        }
    }

}

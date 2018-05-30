package com.nicky.shiro.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nicky.shiro.business.entity.bo.ResourcesBO;
import com.nicky.shiro.business.service.SysResourcesService;
import com.nicky.shiro.business.vo.ResourceConditionVO;
import com.nicky.shiro.persistence.beans.SysResources;
import com.nicky.shiro.persistence.mapper.SysResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统资源
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class SysResourcesServiceImpl implements SysResourcesService {

    @Autowired
    private SysResourceMapper resourceMapper;

    /**
     * 分页查询
     */
    @Override
    public PageInfo<ResourcesBO> findPageBreakByCondition(ResourceConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysResources> sysResources = resourceMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysResources)) {
            return null;
        }
        List<ResourcesBO> resources = sysResources.stream().map(ResourcesBO::new).collect(Collectors.toList());
        PageInfo bean = new PageInfo<>(sysResources);
        bean.setList(resources);
        return bean;
    }

    /**
     * 获取用户的资源列表
     */
    @Override
    public List<ResourcesBO> listUserResources(Map<String, Object> map) {
        List<SysResources> sysResources = resourceMapper.listUserResources(map);
        if (CollectionUtils.isEmpty(sysResources)) {
            return Collections.emptyList();
        }
        List<ResourcesBO> resources = Lists.newArrayListWithExpectedSize(sysResources.size());
        for (SysResources r : sysResources) {
            resources.add(new ResourcesBO(r));
        }
        return resources;
    }

    /**
     * 获取ztree使用的资源列表
     */
    @Override
    public List<Map<String, Object>> queryResourcesListWithSelected(Long rid) {
        List<SysResources> sysResources = resourceMapper.queryResourcesListWithSelected(rid);
        if (CollectionUtils.isEmpty(sysResources)) {
            return Collections.emptyList();
        }
        List<Map<String, Object>> mapList = Lists.newArrayList();
        Map<String, Object> map;
        for (SysResources resources : sysResources) {
            map = Maps.newHashMapWithExpectedSize(3);
            map.put("id", resources.getId());
            map.put("pId", resources.getParentId());
            map.put("checked", resources.getChecked());
            map.put("name", resources.getName());
            mapList.add(map);
        }
        return mapList;
    }

    /**
     * 获取资源的url和permission
     */
    @Override
    public List<ResourcesBO> listUrlAndPermission() {
        List<SysResources> sysResources = resourceMapper.listUrlAndPermission();
        return getResources(sysResources);
    }

    /**
     * 获取所有可用的菜单资源
     */
    @Override
    public List<ResourcesBO> listAllAvailableMenu() {
        List<SysResources> sysResources = resourceMapper.listAllAvailableMenu();
        return getResources(sysResources);
    }

    /**
     * 获取父级资源下所有menu资源
     */
    @Override
    public List<Map<String, Object>> listChildMenuByPid(Long pid) {
        List<SysResources> sysResources = resourceMapper.listMenuResourceByPid(pid);
        if(CollectionUtils.isEmpty(sysResources)){
            return Collections.emptyList();
        }
        List<Map<String, Object>> result = Lists.newLinkedList();
        Map<String, Object> item;
        for (SysResources sysResource : sysResources) {
            item = Maps.newHashMapWithExpectedSize(2);
            item.put("value", sysResource.getId());
            item.put("text", sysResource.getName());
            result.add(item);
        }
        return result;
    }

    /**
     * 获取用户关联的所有资源
     */
    @Override
    public List<ResourcesBO> listByUserId(Long userId) {
        List<SysResources> sysResources = resourceMapper.listByUserId(userId);
        return getResources(sysResources);
    }

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     */
    @Override
    public ResourcesBO insert(ResourcesBO entity) {
        Assert.notNull(entity, "Resources不可为空！");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        resourceMapper.insert(entity.getSysResources());
        return entity;
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     */
    @Override
    public void insertList(List<ResourcesBO> entities) {
        Assert.notNull(entities, "entities不可为空！");
        List<SysResources> sysResources = Lists.newArrayListWithExpectedSize(entities.size());
        for (ResourcesBO resources : entities) {
            resources.setUpdateTime(new Date());
            resources.setCreateTime(new Date());
            sysResources.add(resources.getSysResources());
        }
        resourceMapper.insertList(sysResources);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     */
    @Override
    public boolean removeByPrimaryKey(Long primaryKey) {
        return resourceMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     */
    @Override
    public boolean update(ResourcesBO entity) {
        Assert.notNull(entity, "Resources不可为空！");
        entity.setUpdateTime(new Date());
        return resourceMapper.updateByPrimaryKey(entity.getSysResources()) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     */
    @Override
    public boolean updateSelective(ResourcesBO entity) {
        Assert.notNull(entity, "Resources不可为空！");
        entity.setUpdateTime(new Date());
        return resourceMapper.updateByPrimaryKeySelective(entity.getSysResources()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     */
    @Override
    public ResourcesBO getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        SysResources sysResources = resourceMapper.selectByPrimaryKey(primaryKey);
        return null == sysResources ? null : new ResourcesBO(sysResources);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     */
    @Override
    public ResourcesBO getOneByEntity(ResourcesBO entity) {
        Assert.notNull(entity, "User不可为空！");
        SysResources sysResources = resourceMapper.selectOne(entity.getSysResources());
        return null == sysResources ? null : new ResourcesBO(sysResources);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     */
    @Override
    public List<ResourcesBO> listAll() {
        List<SysResources> sysResources = resourceMapper.selectAll();
        return getResources(sysResources);
    }

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     */
    @Override
    public List<ResourcesBO> listByEntity(ResourcesBO entity) {
        Assert.notNull(entity, "Resources不可为空！");
        List<SysResources> sysResources = resourceMapper.select(entity.getSysResources());
        return getResources(sysResources);
    }

    private List<ResourcesBO> getResources(List<SysResources> sysResources) {
        if (CollectionUtils.isEmpty(sysResources)) {
            return Collections.emptyList();
        }
        return sysResources.stream().map(ResourcesBO::new).collect(Collectors.toList());
    }
}

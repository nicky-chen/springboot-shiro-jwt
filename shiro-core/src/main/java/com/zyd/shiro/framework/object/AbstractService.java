package com.zyd.shiro.framework.object;

import java.util.List;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public interface AbstractService<T, PK> {

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     */
    T insert(T entity);

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     */
    void insertList(List<T> entities);

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     */
    boolean removeByPrimaryKey(PK primaryKey);

    /**
     * 根据主键更新实体全部字段，null值会被更新
     */
    boolean update(T entity);

    /**
     * 根据主键更新属性不为null的值
     */
    boolean updateSelective(T entity);

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     */
    T getByPrimaryKey(PK primaryKey);

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     */
    T getOneByEntity(T entity);

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     */
    List<T> listAll();

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     */
    List<T> listByEntity(T entity);
}

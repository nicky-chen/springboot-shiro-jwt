package com.nicky.shiro.persistence.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author nicky_chin [shuilianpiying@163.com]
 * @since --created on 2018/5/23 at 10:09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SysUserMapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void selectAll() {
        sysUserMapper.selectAll().forEach(System.err::println);
       // sysUserMapper.deleteByPrimaryKey(100L);

    }

    @Test
    public void findPageBreakByCondition() {
    }

    @Test
    public void listByRoleId() {
        sysUserMapper.listByRoleId(1L);
    }
}

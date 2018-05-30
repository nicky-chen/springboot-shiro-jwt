package com.nicky.shiro;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroAdminApplicationTests {

	@Test
	public void contextLoads() {
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(16);
        List<Integer> list = Lists.newArrayListWithExpectedSize(16);
	}

}

package com.nicky.shiro;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroAdminApplicationTests {

    public static void main(String[] args) {
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(16);
        List<Integer> list = Lists.newArrayListWithExpectedSize(16);
        Object a = null;
        map.put("error", "7777");
        List<String> l = ImmutableList.of("a", "b", "c");
        System.out.println(l);
        int aa = 1;
        int bb = 3;
        int cc = 5;
        System.out.println(Range.closedOpen(aa, cc).contains(5));

        try {
            //Preconditions.checkNotNull(a, map);
            Preconditions.checkArgument(!Strings.isNullOrEmpty(""), "用户名不能为空");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
	}

}

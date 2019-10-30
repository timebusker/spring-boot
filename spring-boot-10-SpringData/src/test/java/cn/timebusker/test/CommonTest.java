package cn.timebusker.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:CommonTest
 * @Author:Administrator
 * @Date2019/10/30 20:11
 **/
public class CommonTest {

    @Test
    public void test1() {
        Map<String, Object> map = new HashMap<>();
        map.put("1", 1);

        System.out.println(map.get("1"));
        System.out.println(map.get("2").toString());
    }
}
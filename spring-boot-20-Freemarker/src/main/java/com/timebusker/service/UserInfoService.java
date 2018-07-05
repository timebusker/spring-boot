package com.timebusker.service;

import com.timebusker.entity.UserInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @DESC:UserInfoService
 * @author:timebusker
 * @date:2018/6/23
 */
@Service
public class UserInfoService {
    private static Map<String, UserInfo> hu = new HashMap<>();

    static {
        UserInfo u1 = new UserInfo("1", "timebusker", "123456");
        UserInfo u2 = new UserInfo("2", "yujiaojiao", "123456");
        UserInfo u3 = new UserInfo("3", "mine", "123456");
        UserInfo u4 = new UserInfo("4", "admin", "123456");
        UserInfo u5 = new UserInfo("5", "popo", "123456");
        hu.put(u1.getUid(), u1);
        hu.put(u2.getUid(), u2);
        hu.put(u3.getUid(), u3);
        hu.put(u4.getUid(), u4);
        hu.put(u5.getUid(), u5);
        System.out.println("##########################################################\n\n");
        System.out.println(hu.values().iterator().next().getUsername());
        System.out.println("\n\n##########################################################");
    }

    public Map<String, UserInfo> allUser() {
        return hu;
    }

    public UserInfo getUser() {
        return hu.get("1");
    }

    public Map<String, UserInfo> updateUser(UserInfo u) {
        hu.remove(u.getUid());
        hu.put(u.getUid(), u);
        return hu;
    }

    public Map<String, UserInfo> deleteUser(UserInfo u) {
        hu.remove(u.getUid());
        return hu;
    }
}

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
        UserInfo u1 = new UserInfo("timebusker", "123456");
        UserInfo u2 = new UserInfo("yujiaojiao", "123456");
        UserInfo u3 = new UserInfo("mine", "123456");
        UserInfo u4 = new UserInfo("admin", "123456");
        UserInfo u5 = new UserInfo("popo", "123456");
        hu.put(u1.getUid(), u1);
        hu.put(u2.getUid(), u2);
        hu.put(u3.getUid(), u3);
        hu.put(u4.getUid(), u4);
        hu.put(u5.getUid(), u5);
    }

    public static Map<String, UserInfo> addUser(String username, String password) {
        UserInfo u = new UserInfo(username, password);
        hu.put(u.getUid(), u);
        return hu;
    }

    public static Map<String, UserInfo> allUser() {
        return hu;
    }

    public static Map<String, UserInfo> updateUser(UserInfo u) {
        hu.remove(u.getUid());
        hu.put(u.getUid(), u);
        return hu;
    }

    public static Map<String, UserInfo> deleteUser(UserInfo u) {
        hu.remove(u.getUid());
        return hu;
    }
}

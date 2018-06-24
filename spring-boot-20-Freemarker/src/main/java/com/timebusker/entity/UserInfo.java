package com.timebusker.entity;

/**
 * @DESC:UserInfo
 * @author:timebusker
 * @date:2018/6/23
 */
public class UserInfo {
    private String username;
    private String password;
    private String uid;

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
        this.uid = System.currentTimeMillis() + "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return String.format("UserInfo:[uid:%s,username:%d,password:d%]", uid, username, password);
    }
}

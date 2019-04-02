package cn.timebusker.model;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Date;

/**
 * @DESC:UserEntity:人员信息实体类：
 *
 * 要缓存的 Java 对象必须实现 Serializable 接口，因为 Spring 会将对象先序列化再存入 Redis，不实现 Serializable 的话将会遇到类似这种错误：
 * nested exception is java.lang.IllegalArgumentException
 *
 * 实现Comparable接口可以完成集合排序功能
 *
 * @author:timebusker
 * @date:2019/4/1
 */
public class UserEntity implements Serializable, Comparable {

    private long id;
    private String name;
    private int age;
    private Date createTime;

    /**
     * 重写equals对象方法，用于判断对象是否相等（list 中使用 remove时有用）
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        UserEntity user = (UserEntity) obj;
        System.err.println("相等运算：\t" + JSON.toJSONString(this));
        System.err.println("相等运算：\t" + JSON.toJSONString(user));
        return this.id == user.getId();
    }

    @Override
    public int compareTo(Object obj) {
        UserEntity user = (UserEntity) obj;
        System.err.println("比较运算：\t" + JSON.toJSONString(this));
        System.err.println("比较运算：\t" + JSON.toJSONString(user));
        if (this.id > user.getId()) {
            return 0;
        } else if ((this.id == user.getId()) && user.createTime.compareTo(this.createTime) > 0) {
            return 0;
        } else {
            return 1;
        }
    }

    public UserEntity() {
    }

    public UserEntity(long id, String name, int age, Date createTime) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

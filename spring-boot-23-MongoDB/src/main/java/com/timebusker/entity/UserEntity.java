package com.timebusker.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @DESC:UserEntity
 * @author:timebusker
 * @date:2018/9/5
 */

/**
 * 指定MongoDB中集合名称，不指定默认使用类名。
 */
@Document(collection = "tb_user")
@Data
@ToString
public class UserEntity implements Serializable {

    // 指定主键
    @Id
    private Integer id;
    private String name;
    private Integer age;

    public UserEntity() {
        super();
    }

    public UserEntity(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

package com.timebusker.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @DESC:PersonEntity
 * @author:timebusker
 * @date:2018/9/6
 */
@Document(collection = "tb_person")
@Data
@ToString
public class PersonEntity implements Serializable {

    // 指定主键
    @Id
    private Integer id;
    private String name;
    private Integer age;

    public PersonEntity(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

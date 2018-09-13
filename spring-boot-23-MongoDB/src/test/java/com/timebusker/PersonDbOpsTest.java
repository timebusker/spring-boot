package com.timebusker;

import com.timebusker.entity.PersonEntity;
import com.timebusker.repository.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @DESC:PersonDbOpsTest
 * @author:timebusker
 * @date:2018/9/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonDbOpsTest {

    @Autowired
    private PersonRepository repository;

    /**
     * SAVE:主键重复则为更新操作，不重复则为插入新增操作
     * insert:只进行插入操作，若主键重复则抛异常
     */
    @Test
    public void InsertDb() {
        for (int i = 0; i < 50; i++) {
            PersonEntity person = new PersonEntity(i, "timebuser__" + i, i * 10);
            repository.save(person);
            System.err.println(person.toString());
        }
    }

    @Test
    public void updateDb() {
        for (int i = 0; i < 20; i++) {
            PersonEntity person = new PersonEntity(i, "AAAAAAAAAA__" + i, i * 10);
            repository.save(person);
            System.err.println(person.toString());
        }
    }

    @Test
    public void deleteDB() {
        repository.delete(1);
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1);
        PersonEntity person = (PersonEntity) repository.findOneByParams(params);
        System.err.println(person);
    }

    @Test
    public void findOneByParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1);
        PersonEntity person = (PersonEntity) repository.findOneByParams(params);
        System.err.println(person);
    }

    @Test
    public void findByParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("id", 40);
        List<PersonEntity> list = repository.findByParams(params);
        for (PersonEntity person : list) {
            System.err.println(person.toString());
        }
    }

    /**
     * 指定查询第2页数据
     */
    @Test
    public void findWithPageByParams() {
        Map<String, Object> params = new HashMap<>();
        params.put("id", 40);
        List<PersonEntity> list = repository.findWithPageByParams(params);
        for (PersonEntity person : list) {
            System.err.println(person.toString());
        }
    }
}

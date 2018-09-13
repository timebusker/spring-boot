package com.timebusker;

import com.timebusker.entity.UserEntity;
import com.timebusker.repository.UserRepository;
import net.minidev.json.reader.JsonWriterI;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @DESC:UserDbOpsTest
 * @author:timebusker
 * @date:2018/9/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDbOpsTest {

    @Autowired
    private UserRepository dao;

    @Test
    public void testInsert() {
        UserEntity u = new UserEntity(1, "timebusker", 21);
        dao.save(u);
    }

    @Test
    public void testQuery() {
        System.out.println("****************************\n\n");
        System.out.println(dao.findById(1));
        System.out.println("\n\n****************************");
    }

    @Test
    public void testUpdate(){
        UserEntity u = new UserEntity(1, "timebusker", 21);
        dao.save(u);
    }

    @Test
    public void testDelete(){
        UserEntity u = new UserEntity(1, "timebusker", 21);
        dao.deleteById(1);
    }
}

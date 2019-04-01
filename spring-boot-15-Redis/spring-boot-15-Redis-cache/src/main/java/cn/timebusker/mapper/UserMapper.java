package cn.timebusker.mapper;

import cn.timebusker.model.UserEntity;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @DESC:UserMapper:模拟数据库操作接口
 * @author:timebusker
 * @date:2019/4/1
 */
@Repository
public class UserMapper {

    private static final Logger logger = LoggerFactory.getLogger(UserMapper.class);

    private final static List<UserEntity> users = new ArrayList<>();

    public List<UserEntity> queryUsers() {
        logger.info("--------------数据操作----------------");
        synchronized (users) {
            return users;
        }
    }

    public List<UserEntity> addUser(UserEntity user) {
        logger.info("--------------数据操作----------------");
        synchronized (users) {
            users.add(user);
        }
        return users;
    }

    public List<UserEntity> deleteUsers(UserEntity user) {
        logger.info("--------------数据操作----------------");
        synchronized (users) {
            users.remove(user);
        }
        return users;
    }

    public List<UserEntity> updateUser(UserEntity user) {
        logger.info("--------------数据操作----------------");
        synchronized (users) {
            users.remove(user);
            users.add(user);
        }
        return users;
    }

    public static void main(String[] args) {
        UserMapper mapper = new UserMapper();
        mapper.addUser(new UserEntity(1l, "a", 1, new Date()));
        mapper.addUser(new UserEntity(2l, "b", 2, new Date()));
        System.out.println(JSON.toJSONString(users));
        mapper.deleteUsers(new UserEntity(2l, "b", 2, new Date()));
        System.out.println(JSON.toJSONString(users));
        mapper.updateUser(new UserEntity(2l, "d", 2, new Date()));
        System.out.println(JSON.toJSONString(users));
    }
}

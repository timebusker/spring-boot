package cn.timebusker.service;

import cn.timebusker.mapper.UserMapper;
import cn.timebusker.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @DESC:UserService:用户操作业务类
 * @author:timebusker
 * @date:2019/4/1
 */
@Service
@CacheConfig(cacheNames = "users")
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Cacheable()
    public List<UserEntity> getList() {
        return mapper.queryUsers();
    }

    @CachePut()
    public List<UserEntity> addUser(UserEntity user) {
        return mapper.addUser(user);
    }

    @CacheEvict(allEntries = true)
    public List<UserEntity> deleteUser(UserEntity user) {
        return mapper.deleteUsers(user);
    }

    @CachePut()
    public List<UserEntity> updateUser(UserEntity user) {
        return mapper.updateUser(user);
    }
}

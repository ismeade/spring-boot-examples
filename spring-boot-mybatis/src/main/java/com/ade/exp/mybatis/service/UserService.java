package com.ade.exp.mybatis.service;

import com.ade.exp.mybatis.entity.User;
import com.ade.exp.mybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "userService")
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Cacheable(key = "'id_' + #id")
    public User select(int id){
        return userMapper.select(id);
    }

    public List<User> findAll() {
        return userMapper.findAll();
    }

}

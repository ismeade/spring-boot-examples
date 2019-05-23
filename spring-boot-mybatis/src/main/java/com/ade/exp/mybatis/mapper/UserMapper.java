package com.ade.exp.mybatis.mapper;

import com.ade.exp.mybatis.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    User select(int id);

    List<User> findAll();

}

package com.ade.exp.mybatis.controller;

import com.ade.exp.mybatis.entity.User;
import com.ade.exp.mybatis.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/testBoot")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("list")
    public String list() {
        List<User> userList = userService.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "success");
        map.put("data", userList);
        map.put("count", userList.size());
        return JSON.toJSONString(map);
    }

    @RequestMapping("getUser/{id}")
    public String GetUser(@PathVariable int id){
        return userService.select(id).toString();
    }

}

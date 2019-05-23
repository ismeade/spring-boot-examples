package com.ade.exp.mybatis;

import com.ade.exp.mybatis.entity.User;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private StringRedisTemplate redisTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test() {
		String value = redisTemplate.opsForValue().get("userService::id_1");
		System.out.println(value);
		User user = JSON.parseObject(value, User.class);
		System.out.println(user);
	}

}

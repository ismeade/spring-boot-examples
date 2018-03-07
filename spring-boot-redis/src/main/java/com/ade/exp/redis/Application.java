package com.ade.exp.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Created by liyang on 17-11-9.
 */
@SpringBootApplication
@RestController
public class Application {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("/set")
    public String set() {
        redisTemplate.opsForValue().set("a", "2", 100, TimeUnit.SECONDS);
        return "ok";
    }

    @RequestMapping("/get")
    public String get() {
        return redisTemplate.opsForValue().get("a");
    }

    @RequestMapping("/del")
    public String del() {
        redisTemplate.delete("a");
        return "ok";
    }

}

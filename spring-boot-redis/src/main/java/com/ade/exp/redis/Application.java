package com.ade.exp.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
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

    @RequestMapping("/list-init")
    public String listInit() {
        redisTemplate.opsForList().rightPushAll("list", "1", "2", "3", "4", "5", "6", "7", "8");
        return "ok";
    }

    @RequestMapping("/list-lpop")
    public String listLpop() {
        Executor executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            executor.execute(() -> System.out.println(redisTemplate.opsForList().leftPop("list")));
        }
        //return redisTemplate.opsForList().leftPop("list");
        return "ok";
    }

}

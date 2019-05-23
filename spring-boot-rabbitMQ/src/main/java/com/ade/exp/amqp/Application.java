package com.ade.exp.amqp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liyang on 17-11-9.
 */
@SpringBootApplication
@RestController
public class Application {

    @Autowired
    private TopicSender topicSender;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("send")
    public String send() {
        return topicSender.sendC("test1234567");
        //return "ok";
    }

    @RequestMapping("send_b")
    public String sendB() {
        topicSender.sendB();
        return "ok";
    }

}

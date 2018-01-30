package com.ade.exp.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by liyang on 17-11-9.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Application.class, args);
        //TopicSender sender = context.getBean(TopicSender.class);
        //sender.sendA();
        //sender.sendB();
        //sender.test();
    }

}

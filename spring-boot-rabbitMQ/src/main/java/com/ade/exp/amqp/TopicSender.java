package com.ade.exp.amqp;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by liyang on 17-11-9.
 */
@Component
public class TopicSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void sendA() {
        String context = "{\"body\":{\"seq\":\"00000100000333401011512640921175_0\"}}";
        System.out.println("Sender : " + context);
        //this.rabbitTemplate.convertAndSend("test", "topic.a", context);
        this.rabbitTemplate.convertAndSend("pay-service", "topic.pay.refund.launch", context);
    }

    public void sendB() {
        String context = "hi, i am message 1";
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("test", "topic.b", context);
    }

    @Value("${config}")
    private String config;

    public void test() {
        System.out.println(config);
    }

}

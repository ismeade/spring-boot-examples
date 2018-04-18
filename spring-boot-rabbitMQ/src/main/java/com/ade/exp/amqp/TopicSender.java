package com.ade.exp.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * Created by liyang on 17-11-9.
 */
@Component
public class TopicSender {

    //@Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setAmqpTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.rabbitTemplate.setConfirmCallback((correlationData, b, s) -> {
            System.out.println("callback " + correlationData + " | " + s);
            if (b) {
                System.out.println("success");
            } else {
                System.out.println("fail");
            }
        });
    }

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

    public String sendC(String msg) {

        Message sendMessage = MessageBuilder.withBody(msg.getBytes()).build();
        sendMessage.getMessageProperties().setReceivedExchange(TopicRabbitConfig.REPLY_EXCHANGE_NAME);
        sendMessage.getMessageProperties().setReceivedRoutingKey(TopicRabbitConfig.REPLY_MESSAGE_KEY);
        sendMessage.getMessageProperties().setRedelivered(true);
        sendMessage.getMessageProperties().setMessageId(UUID.randomUUID().toString());
        sendMessage.getMessageProperties().setTimestamp(new Date());

        Message reMessage = this.rabbitTemplate.sendAndReceive("test", "topic.c", sendMessage);
        return new String(reMessage.getBody());
    }

}

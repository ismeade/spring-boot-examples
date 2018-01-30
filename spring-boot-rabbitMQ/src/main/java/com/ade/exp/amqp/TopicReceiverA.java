package com.ade.exp.amqp;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by liyang on 17-11-9.
 */
@Component
public class TopicReceiverA {

    // 监听queue.a队列
    @RabbitListener(queues = "queue.a")
    public void process1(@Payload String message,
                        @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, // 手动ack
                        Channel channel) // 手动ack
            throws IOException {
        try {
            System.out.println("Topic Receiver A 1 : " + message);
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        } finally {
            channel.basicAck(deliveryTag,false); // 手动ack
        }
    }

    // 监听queue.a队列 和上边方法 轮流得到消息
    @RabbitListener(queues = "queue.a")
    public void process2(@Payload String message,
                        @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, // 手动ack
                        Channel channel) // 手动ack
            throws IOException {
        try {
            System.out.println("Topic Receiver A 2 : " + message);
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e){
            System.out.println(e.getLocalizedMessage());
        } finally {
            channel.basicAck(deliveryTag,false); // 手动ack
        }
    }
}
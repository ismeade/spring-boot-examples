package com.ade.exp.amqp;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by liyang on 17-11-9.
 */
@Component
public class TopicReceiverB {

    @RabbitListener(queues = "queue.b")
    public void process(Message message,
                        @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, // 手动ack
                        Channel channel) // 手动ack
            throws IOException{
                System.out.println(new String(message.getBody()));
        System.out.println("Topic Receiver B  : " + message);
        channel.basicAck(deliveryTag, false);
    }

}

package com.ade.exp.amqp;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liyang on 17-11-9.
 */
@Configuration
public class TopicRabbitConfig {

    final static String queueA = "queue.a";
    final static String queueB = "queue.b";

    @Bean
    Queue queueA() {
        // 创建队列
        return new Queue(TopicRabbitConfig.queueA, true, false, false);
    }

    @Bean
    Queue queueB() {
        return new Queue(TopicRabbitConfig.queueB, true, false, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("test", true, false);
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueA, TopicExchange exchange) {
        // 将queueA队列绑定到topic.a主题
        return BindingBuilder
                .bind(queueA)
                .to(exchange)
                .with("topic.a"); // 多个队列可以绑定到相同的routingKey，实现广播式
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueB, TopicExchange exchange) {
        return BindingBuilder
                .bind(queueB)
                .to(exchange)
                .with("topic.b");
    }

}

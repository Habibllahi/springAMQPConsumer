package com.codetrik.springAMQPConsumer.amqp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * You need to register this component to a bean of SimpleMessageListenerContainer
 */
@Getter
@Setter
@RequiredArgsConstructor
@Component
public class SimpleConsumer implements MessageListener {
    @Override
    public void onMessage(Message message) {
        System.out.println("Consumer >> "+new String(message.getBody()));
    }
}

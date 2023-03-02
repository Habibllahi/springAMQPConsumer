package com.codetrik.springAMQPConsumer.config;

import com.codetrik.springAMQPConsumer.amqp.Consumer;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AMQPConfig {

    //Create a Queue
    @Bean
    public Queue createQueue(@Value("${com.codetrik.amqp.queue}") String name){
        return new Queue(name,false);
    }

    //This bean is necessary when not using @RabbitListener annotation in creating listener
    @Bean
    public SimpleMessageListenerContainer container(@Value("${com.codetrik.amqp.queue}") String queue, ConnectionFactory cf,
                                                    Consumer consumer){
        var smlc = new SimpleMessageListenerContainer(cf);
        smlc.addQueueNames(queue);
        smlc.setMessageListener(consumer);
        return smlc;
    }
}

package com.codetrik.springAMQPConsumer.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AnnotatedConsumer {

    @RabbitListener(queues = {"${com.codetrik.amqp.queue}"})
    public void consume(String message){
        System.out.println("annotatedConsumer>> "+message);
    }
}

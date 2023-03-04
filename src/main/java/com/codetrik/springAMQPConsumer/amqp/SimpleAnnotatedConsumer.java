package com.codetrik.springAMQPConsumer.amqp;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class SimpleAnnotatedConsumer {

    @RabbitListener(queues = {"${com.codetrik.amqp.queue}"})
    public void consume(String message){
        System.out.println("annotatedConsumer>> "+message);
    }

    @RabbitListener(queues = {"${com.codetrik.amqp.rpc-queue}"})
    @SendTo({"${com.codetrik.amqp.reply-to-queue}"})
    public Message rpcConsume(Message message){
        System.out.println("annotatedRPCConsumer>> "+new String(message.getBody()));
        return MessageBuilder.withBody("Acknowledge".getBytes(StandardCharsets.UTF_8))
                .setCorrelationId(message.getMessageProperties().getCorrelationId())
                .build();
    }
}

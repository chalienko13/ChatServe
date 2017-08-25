package com.superteam.notification.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {
    private static final Logger log = LoggerFactory.getLogger(EmailConsumer.class);

    @RabbitListener(queues="${myqueue}")
    public void handler(String message){
        log.info("EmailConsumer > " + message);
    }
}

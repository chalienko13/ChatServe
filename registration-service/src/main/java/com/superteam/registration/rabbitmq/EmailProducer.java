package com.superteam.registration.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailProducer {
    private static final Logger log = LoggerFactory.getLogger(EmailProducer.class);

    public final RabbitTemplate rabbitTemplate;

    @Autowired
    public EmailProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendTo(String routingkey, String message) {
        log.info("Sending> ...");
        this.rabbitTemplate.convertAndSend(routingkey, message);
    }
}
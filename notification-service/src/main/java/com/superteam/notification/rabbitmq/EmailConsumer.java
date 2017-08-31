package com.superteam.notification.rabbitmq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.superteam.notification.service.EmailService;
import com.superteam.notification.service.helpers.EmailHelper;
import com.superteam.registration.dto.UserRegConfirmDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmailConsumer {
    private static final Logger log = LoggerFactory.getLogger(EmailConsumer.class);

    private final EmailService emailService;
    private final EmailHelper emailHelper;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public EmailConsumer(EmailService emailService, EmailHelper emailHelper) {
        this.emailService = emailService;
        this.emailHelper = emailHelper;
    }

    @RabbitListener(queues="${register}")
    public void handler(String message){
        log.info("EmailConsumer > {}", message);
        UserRegConfirmDto userConfirm;
        try {
            userConfirm = mapper.readValue(message, UserRegConfirmDto.class);
            emailService.sendEmail(
                    userConfirm.getEmail(),
                    emailHelper.getRegConfirmSubj(),
                    emailHelper.getRegConfirmBody(userConfirm.getUsername(), userConfirm.getActivationLink())
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

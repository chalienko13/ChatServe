package com.superteam.notification.service.impl;

import com.superteam.notification.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    private final JavaMailSenderImpl emailSender;

    @Autowired
    public EmailServiceImpl(JavaMailSenderImpl emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        LOGGER.debug("Start sendConfirmRegistration");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
//        emailSender.send(message);

        LOGGER.debug("Email host {}", emailSender.getHost());
        LOGGER.debug("Email username {}", emailSender.getUsername());
        LOGGER.debug("Email password {}", emailSender.getPassword());
    }
}

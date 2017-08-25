package com.superteam.notification.service.impl;

import com.superteam.notification.service.EmailService;

import org.springframework.mail.javamail.JavaMailSenderImpl;

public class EmailServiceImpl implements EmailService {

    private JavaMailSenderImpl javaMailSender;

    @Override
    public void sendConfirmRegistration() {

    }
}

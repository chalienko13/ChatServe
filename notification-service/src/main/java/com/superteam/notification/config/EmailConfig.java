package com.superteam.notification.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.Resource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:email.properties")
public class EmailConfig {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailConfig.class);

    private static final String PROP_EMAIL_HOST = "mail.smtp.host";
    private static final String PROP_EMAIL_PORT = "mail.smtp.port";
    private static final String PROP_EMAIL_AUTH = "mail.smtp.auth";
    private static final String PROP_EMAIL_PASSWORD = "mail.password";
    private static final String PROP_EMAIL_USERNAME = "mail.username" ;
    private static final String PROP_EMAIL_STARTTLS = "mail.smtp.starttls.enable";


    @Resource
    private Environment env;

    @Bean
    public JavaMailSenderImpl getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(env.getProperty(PROP_EMAIL_HOST));
        mailSender.setPort(Integer.valueOf(env.getProperty(PROP_EMAIL_PORT)));
        mailSender.setUsername(env.getProperty(PROP_EMAIL_USERNAME));
        mailSender.setPassword(env.getProperty(PROP_EMAIL_PASSWORD));
        mailSender.setJavaMailProperties(getProperties());

        LOGGER.debug("Email sender is successful prepared");

        return mailSender;
    }


    public Properties getProperties(){
        Properties props = new Properties();
        props.put(PROP_EMAIL_AUTH, env.getProperty(PROP_EMAIL_AUTH));
        props.put(PROP_EMAIL_STARTTLS, env.getProperty(PROP_EMAIL_STARTTLS));
        return props;
    }
}

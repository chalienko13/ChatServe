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
    public JavaMailSenderImpl mailSender(){
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(env.getRequiredProperty(PROP_EMAIL_HOST));

        javaMailSender.setUsername(env.getRequiredProperty(PROP_EMAIL_USERNAME));
        javaMailSender.setPassword(env.getRequiredProperty(PROP_EMAIL_PASSWORD));

        Properties props = new Properties();
        props.put(PROP_EMAIL_AUTH, env.getRequiredProperty(PROP_EMAIL_AUTH));
        props.put(PROP_EMAIL_STARTTLS, env.getRequiredProperty(PROP_EMAIL_STARTTLS));
        props.put(PROP_EMAIL_HOST, env.getRequiredProperty(PROP_EMAIL_HOST));
        props.put(PROP_EMAIL_PORT, env.getRequiredProperty(PROP_EMAIL_PORT));
        javaMailSender.setJavaMailProperties(props);

        return javaMailSender;
    }
}

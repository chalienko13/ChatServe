package com.superteam.notification.service.helpers;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@PropertySource("classpath:email.properties")
public class EmailHelper {
    private static final String REGISTER_CONFIRM_SUBJECT = "subject.register.confirm";
    private static final String REGISTER_CONFIRM_BODY = "body.register.confirm";


    @Resource
    private Environment env;

    public String getRegConfirmSubj(){
        return env.getProperty(REGISTER_CONFIRM_SUBJECT);
    }

    public String getRegConfirmBody(String username, String link){
        return String.format(env.getProperty(REGISTER_CONFIRM_BODY), username, link);
    }
}
